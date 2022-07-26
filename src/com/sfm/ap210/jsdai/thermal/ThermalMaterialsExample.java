package com.sfm.ap210.jsdai.thermal;

import java.io.File;
import java.util.Map;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EComposite_shape_aspect;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EPackage;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProperty_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ERepresentation_context;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_aspect;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_representation;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EThermal_network;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiModel;
import jsdai.lang.SdaiRepository;
import jsdai.lang.SdaiSession;

import com.sfm.ap210.jsdai.MIMfunctionalQueriesImpl;
import com.sfm.ap210.jsdai.MIMpackageQueries;
import com.sfm.ap210.jsdai.MIMpackageQueriesImpl;
import com.sfm.ap210.jsdai.functional.Functional_unit_network_definition;
import com.sfm.ap210.jsdai.functional.Functional_unit_network_node_definition;
import com.sfm.ap210.jsdai.functional.Thermal_functional_unit_network_node_definition;
import com.sfm.ap210.jsdai.param.Param;
import com.sfm.ap210.jsdai.utils.SdaiUtils;
/**
 * Example script to show the extraction of material properties from 
 * an AP210 STEP model.  
 */
public class ThermalMaterialsExample {

	static void printUsageAndExit() {
		System.out.println("No file provided.");
		System.out.println("Provide filepath of STEP file as command line argument");
		System.exit(1);
	}
	
	static void printAndExit(String msg) {
		System.out.println(msg);
		System.exit(0);
	}
	
	static void showMaterials(EPackage e_pkg, SdaiModel model) throws SdaiException {
		MIMpackageQueries pq = new MIMpackageQueriesImpl(model);						
				
		EShape_representation shape = pq.get3dThermalAnalysisInputRepresentation(e_pkg);
		if(shape == null) {
			System.out.println("No 3d solid thermal analysis model found in provided file");
			return;
		}			
		
		ERepresentation_context materialsCtx = null;
		for(ERepresentation_context ctx : SdaiUtils.Instances(ERepresentation_context.class, model)) {
			if(ctx.testContext_identifier(null) && "material_properties".equals(ctx.getContext_identifier(null))) {
				materialsCtx = ctx;
				break;
			}
		}
		if(materialsCtx == null) {
			System.out.println("No material characteristics context found in file");
			return;
		}
					
		MaterialExtractor extractor = new MaterialExtractor(model);
		Map<EShape_aspect, ThermalMaterial> materials = extractor.extractMaterials(e_pkg, shape, materialsCtx);
		for(EShape_aspect aspect : materials.keySet()) {
			if(aspect.isKindOf(EComposite_shape_aspect.class))
				continue;
			System.out.println(String.format("\t%s", aspect.getName(null)));
			ThermalMaterial material = materials.get(aspect);
			for(String property : material.getProperties()) {
				Param value = material.getProperty(property);
				System.out.println(String.format("\t\t%s", value));
			}
		}									
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
	
	static void showNetNodes(EPackage e_pkg, SdaiModel model) throws SdaiException {
		MIMpackageQueries pq = new MIMpackageQueriesImpl(model);
		EThermal_network e_net = pq.getThermalNetworkOfPackage(e_pkg);
		
		if(e_net == null) {
			System.out.println("No thermal network instance found in provided file");
			return;
		}				
		
		MIMfunctionalQueriesImpl mimQ = new MIMfunctionalQueriesImpl(model);
		Functional_unit_network_definition net = mimQ.getFunctionalUnitNetworkDefinition(e_net);				
		
		for(Functional_unit_network_node_definition node : net.getNodes()) {
			printNode(node);
		}				
	}
	
	static void run(File file) throws SdaiException {
		SdaiSession session = null;
		try {
			session = SdaiUtils.newRWSession();			
			SdaiRepository repo = SdaiUtils.newRepo(session, "examples");			
			SdaiModel model = SdaiUtils.loadModel(file, repo);
			EPackage e_pkg = SdaiUtils.findFirst(EPackage.class, model);
			if(e_pkg == null) {
				printAndExit("No PACKAGE instance found in provided file.");
			}
			System.out.println("Package: " + e_pkg.getName((EProperty_definition)null));
			System.out.println("Materials:");
			showMaterials(e_pkg, model);
			System.out.println("Network Nodes:");
			showNetNodes(e_pkg, model);
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
