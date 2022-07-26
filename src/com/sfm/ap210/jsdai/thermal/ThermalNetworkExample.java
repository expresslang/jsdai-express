package com.sfm.ap210.jsdai.thermal;

import java.io.File;
import java.util.List;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EPackage;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProperty_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EThermal_network;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiModel;
import jsdai.lang.SdaiRepository;
import jsdai.lang.SdaiSession;

import com.sfm.ap210.jsdai.MIMfunctionalQueriesImpl;
import com.sfm.ap210.jsdai.MIMpackageQueries;
import com.sfm.ap210.jsdai.MIMpackageQueriesImpl;
import com.sfm.ap210.jsdai.functional.Functional_unit;
import com.sfm.ap210.jsdai.functional.Functional_unit_network_definition;
import com.sfm.ap210.jsdai.functional.Functional_unit_network_node_definition;
import com.sfm.ap210.jsdai.functional.Thermal_functional_unit_network_node_definition;
import com.sfm.ap210.jsdai.param.Param;
import com.sfm.ap210.jsdai.utils.SdaiUtils;

/**
 * Example script to show the sample extraction and traversal of a {@MIM thermal_network} instance.
 * The script expects a single command-line argument which should be a filepath to a 
 * Part21 STEP file.
 */
public class ThermalNetworkExample {
	
	static void printUsageAndExit() {
		System.out.println("No file provided.");
		System.out.println("Provide filepath of STEP file as command line argument");
		System.exit(1);
	}
	
	static void printAndExit(String msg) {
		System.out.println(msg);
		System.exit(0);
	}
	
	static void printNode(Functional_unit_network_node_definition node) {
		String location = "";
		if(node instanceof Thermal_functional_unit_network_node_definition) {
			Thermal_functional_unit_network_node_definition tnode = 
				(Thermal_functional_unit_network_node_definition) node;
			if(tnode.location != null) {
				location += "(";
				for(double coord : tnode.location) {
					location += " ";
					location += coord;
					location += " ";
				}
				location += ")";
			}
		}		
		System.out.println("Node : "+node.name+" "+location);
	}
	
	static void printResistor(Functional_unit res, String name) {
		Param theta = res.getParamWithModelParameterId("theta");
		System.out.println(name + " : " + theta);
	}
	
	static void run(File file) throws SdaiException {
		SdaiSession session = null;
		try {
			session = SdaiUtils.newRWSession();			
			SdaiRepository repo = SdaiUtils.newRepo(session, "examples");			
			SdaiModel model = SdaiUtils.loadModel(file, repo);
			
			MIMpackageQueries pq = new MIMpackageQueriesImpl(model);
			
			EPackage e_pkg = SdaiUtils.findFirst(EPackage.class, model);
			if(e_pkg == null) {
				printAndExit("No PACKAGE instance found in provided file.");
			}								
			
			EThermal_network e_net = pq.getThermalNetworkOfPackage(e_pkg);
			
			if(e_net == null) {
				printAndExit("No thermal network instance found in provided file");
			}
			
			System.out.println("PACKAGE: " + e_pkg.getName((EProperty_definition)null));
			
			MIMfunctionalQueriesImpl mimQ = new MIMfunctionalQueriesImpl(model);
			Functional_unit_network_definition net = mimQ.getFunctionalUnitNetworkDefinition(e_net);				
			
			List<Functional_unit_network_node_definition> nodes = net.getNodes();
			for(Functional_unit_network_node_definition node : nodes) {
				printNode(node);
			}
			for(int i=0; i < nodes.size()-1; i++) {
				Functional_unit_network_node_definition n_i = nodes.get(i);
				for(int j=i+1; j < nodes.size(); j++) {
					Functional_unit_network_node_definition n_j = nodes.get(j);
					Functional_unit resistor = net.getFunctionalUnitBetween(n_i, n_j);
					if(resistor == null)
						continue;		
					printResistor(resistor, String.format("%s <-> %s", n_i.name, n_j.name));
				}
			}										
		} finally {
			SdaiUtils.close(session);
		}
	}

	public static void main(String[] args) {
		if(args.length == 0) {
			printUsageAndExit();
		}
		String filepath = args[0];
		File file = new File(filepath);
		if(!file.exists()) {
			System.out.println(String.format("Specified file (%s) does not exist.", file.getAbsolutePath()));
			System.exit(1);
		}
		try {
			run(file);
		} catch(Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}


}
