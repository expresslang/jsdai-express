package com.sfm.spice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EFunctional_unit;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProduct_definition_formation;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf;
import jsdai.lang.ASdaiModel;
import jsdai.lang.A_string;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiIterator;
import jsdai.lang.SdaiModel;
import jsdai.lang.SdaiRepository;
import jsdai.lang.SdaiSession;
import jsdai.lang.SdaiTransaction;

import com.sfm.ap210.jsdai.MIMfunctionalQueriesImpl;
import com.sfm.ap210.jsdai.functional.Functional_unit_network_definition;
import com.sfm.ap210.jsdai.write.FunctionalSdaiWriter;
import com.sfm.demo.SpiceReader;

/**
 * A demonstration of the bidirectional SPICE-AP210  prototype mapping implementation.
 * LTspice netlists are parsed into an internal SPICE representation contained in the
 * com.sfm.spice package. The FunctionalToSpiceMapper maps this representation to the 
 * Functional model objoects contained in the com.sfm.ap210.jsdai.functional package.
 * The FunctionalSdaiWriter is used to map these functional model objects to the MIM
 * entities using the JSDAI library.
 * 
 * The process is inverted through use of the MIMfunctionalQueriesImpl and the 
 * SpiceToFunctionalMapper.
 *  
 * @author James Stori
 *
 */
public class BiDirectionalSpiceAP210demo {

	static SdaiSession session = null; 
	static SdaiTransaction transaction = null;
	static SdaiRepository outRepo = null;
	static SdaiRepository inRepo = null;
	
	/**
	 * @param args
	 * @throws SdaiException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws SdaiException, IOException {
		// working directory: LTSpiceDemoFiles
		roundTripSpiceNetlist("MC1648.net");
		roundTripSpiceNetlist("butter.net");
		roundTripSpiceNetlist("Clapp.net");
		roundTripSpiceNetlist("divider_with_subckt.net");
	    
	    log("Done.");
	}
	
	static void roundTripSpiceNetlist(String filePath) throws SdaiException, IOException
	{
		initiateSdai();
		log("Parsing: "+filePath);
		SpiceReader parser = new SpiceReader(filePath);
		
	    SpiceCircuit incircuit = parser.parseSpiceCircuit();
	    
	    
	    log("Ignored Dot commands: \n"+parser.getIgnoredDotCommands());
	    //log("Reporting Input Circuit.");
	    //log(incircuit.toString());
	    
	    SpiceToFunctionalMapper toFunctional = new SpiceToFunctionalMapper(incircuit);
	    Functional_unit_network_definition outFund = toFunctional.mappedFUND();
	    outFund.createUsageView();
	    
	    String outFileName = incircuit.getName()+".stp";
	    writeFUNDtoMIMfile(outFund, outFileName);
	    log("Output AP210: "+outFileName);
	    
	    Functional_unit_network_definition inFund = readFUNDfromFile(incircuit.getName(), outFileName);
	    	    
	    FunctionalToSpiceMapper toSpice = new FunctionalToSpiceMapper(inFund);
	    SpiceCircuit outCircuit = toSpice.mappedCircuit();
	    
	    String outCircuitName = outCircuit.getName()+"_from_AP210.net";
	    log("Output Circuit: "+outCircuitName);
	    //log(outCircuit.toString());
	    //log(outCircuit.toNetFormatString());
	    writeSpiceCircuitToNetFile(outCircuitName, outCircuit, outFileName, parser.getIgnoredDotCommands());
	    terminateSdai();
	}
	
	static void writeSpiceCircuitToNetFile(String outCircuitName, SpiceCircuit circuit, String stepFileName, String dotCommands) throws IOException
	{
		
	    Writer output = new BufferedWriter(new FileWriter(new File(outCircuitName)));
	    try {
	      output.write("* LTSpiceIV Netlist Generated from AP210 file: "+stepFileName+"\n");
	      output.write(circuit.toNetFormatString());
	      output.write("* Appended dot commands extracted from input file.\n");
	      output.write(dotCommands);
	      
	    }
	    finally {
	      output.close();
	    }

	}
	
	static SdaiModel writeFUNDtoMIMfile(Functional_unit_network_definition fund, String name) throws SdaiException
	{
		
	    SdaiModel outModel = newEmptyModel();
	    FunctionalSdaiWriter sdaiWriter = new FunctionalSdaiWriter(outModel);
		
		sdaiWriter.instantiateFunctionalUnitNetworkDefinition(fund);
		exportRepoToFile(name);
		return outModel;
		
	}
	
	static SdaiModel importExistingModel(String inFile) throws SdaiException
	{
		inRepo = session.importClearTextEncoding(null, inFile, null);
		ASdaiModel models = inRepo.getModels();
		SdaiIterator it_models = models.createIterator();
		SdaiModel m = null;
		if (it_models.next())
		{
			m = models.getCurrentMember(it_models);
			//System.out.println("Model <" + m.getName() + "> found");
			return m;
		}
		return null;
	}
	
	static Functional_unit_network_definition readFUNDfromFile(String productID, String filePath) throws SdaiException
	{
		SdaiModel inModel = importExistingModel(filePath);
		return readFUNDfromModel(productID, inModel);
	}
	
	static Functional_unit_network_definition readFUNDfromModel(String productID, SdaiModel model) throws SdaiException
	{
		MIMfunctionalQueriesImpl mimQ = new MIMfunctionalQueriesImpl(model);
		
		//EProduct e_circuit = mimQ.getFunctionalProductWithGivenId(productID);
		EProduct_definition_formation e_circuit_version = mimQ.getFunctionalProductVersionWithGivenProductId(productID);
		EFunctional_unit e_fund = mimQ.getFunctionalUnitNetworkDefinition(e_circuit_version);
		Functional_unit_network_definition fund2 = mimQ.getFunctionalUnitNetworkDefinition(e_fund);
		//EFunctional_unit e_fuuv = mimQ.getFunctionalUnitUsageView(e_circuit_version);
		//log(e_circuit_version);
		//log(e_fund);
		//log(e_fuuv);
		return fund2;
	}
	
	static void initiateSdai() throws SdaiException
	{
		// redirect the J-SDAI system log to System.out 
		SdaiSession.setLogWriter(new PrintWriter(System.out, true)); 

		// first open a session and specify the desired AP 
		session = SdaiSession.openSession(); 

		// start a read/write transaction to allow importClearTextEncoding 
		transaction = session.startTransactionReadWriteAccess(); 

		session = SdaiSession.openSession();
		transaction = session.startTransactionReadWriteAccess();

	}
	
	static void terminateSdai() throws SdaiException
	{
		if (transaction != null)
			transaction.endTransactionAccessAbort(); 
		if (inRepo != null)
		{
			inRepo.closeRepository();
			inRepo.deleteRepository();
		}
		if (outRepo != null)
		{
			outRepo.closeRepository();
			outRepo.deleteRepository();
		}
		if (session != null)
			session.closeSession();	
	}
	
	static SdaiModel newEmptyModel() throws SdaiException
	{
		outRepo = session.createRepository("", null); 
		outRepo.openRepository(); 
		A_string descriptions = outRepo.getDescription(); 
		descriptions.addByIndex(1, "Sample functional network model."); 
		A_string authors = outRepo.getAuthor(); 
		authors.addByIndex(1, "James Stori"); 
		A_string organizations = outRepo.getOrganization(); 
		organizations.addByIndex(1, "SFM Technology, Inc."); 
		outRepo.setOriginatingSystem(session.getSdaiImplementation().getName() + " " + 
		                   session.getSdaiImplementation().getLevel() ); 
		outRepo.setAuthorization("Lothar Klein"); 

		SdaiModel m = outRepo.createSdaiModel("model", SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.class); 
		m.startReadWriteAccess(); 
		return m;
	}
	
	static void exportRepoToFile(String outFile) throws SdaiException
	{
		outRepo.exportClearTextEncoding(outFile);
	}
	
	private static void log(Object aObject)
	{
	    System.out.println(String.valueOf(aObject));
	}

}
