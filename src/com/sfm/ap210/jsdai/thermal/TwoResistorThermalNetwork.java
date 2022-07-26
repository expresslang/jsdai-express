package com.sfm.ap210.jsdai.thermal;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EGeometric_representation_context;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ERepresentation_context;

import com.sfm.ap210.jsdai.functional.FunctionalProductVersion;
import com.sfm.ap210.jsdai.functional.Functional_unit;
import com.sfm.ap210.jsdai.functional.Functional_unit_terminal;
import com.sfm.ap210.jsdai.functional.Functional_unit_usage_view;
import com.sfm.ap210.jsdai.functional.Thermal_functional_unit_network_node_definition;
import com.sfm.ap210.jsdai.functional.Thermal_network_definition;
import com.sfm.ap210.jsdai.param.DerivedSiUnit;
import com.sfm.ap210.jsdai.param.MeasureParamImpl;
import com.sfm.ap210.jsdai.param.ModelParameter;
import com.sfm.ap210.jsdai.param.Param;
import com.sfm.ap210.jsdai.param.ParameterAssignment;
import com.sfm.ap210.jsdai.param.DerivedSiUnit.SiUnitTerm;
import com.sfm.ap210.jsdai.param.SiUnit.SiBase;

public class TwoResistorThermalNetwork {
	Param junction_case_resistance;
	double[] case_point;
	Param junction_board_resistance;
	double[] board_point;
	ERepresentation_context context;
	Functional_unit_usage_view resistorFUUV; // all resistor instances will share a common FUUV.
	ModelParameter resistanceModelParam;
	FunctionalProductVersion networkProduct;
	
	
	public TwoResistorThermalNetwork(String network_product_id, String network_product_name, String network_version, 
			double theta_jb, double theta_jc)
	{
		networkProduct = new FunctionalProductVersion(network_product_id, network_product_name, network_version); 
		junction_case_resistance = networkResistanceParameter(theta_jc);
		junction_board_resistance = networkResistanceParameter(theta_jb);		
		// instantiate a single product / usage view for a thermal resistor.
		// all resistor (Functional_unit) instances will share this usage view
		FunctionalProductVersion resistorProduct = new FunctionalProductVersion("thermal resistor", "resistor", "1");
		resistorFUUV = new Functional_unit_usage_view(resistorProduct);
		resistorFUUV.addTerminal("A");
		resistorFUUV.addTerminal("B");
		
		// instantiate a single model parameter for a resistance value
		resistanceModelParam = new ModelParameter("theta", "thermal resistance", "");
	}
	
	/**
	 * the thermal resistor has two terminals 'A' and 'B'
	 * standard SI unit for thermal resistance is degC/W
	 * @param junction_case_resistance
	 * @param junction_board_resistance
	 */
	public TwoResistorThermalNetwork(String network_product_id, String network_product_name, String network_version, 
			double theta_jb, double[] jb_point, 
			double theta_jc, double[] jc_point,
			ERepresentation_context ctx)
	{
		this(network_product_id, network_product_name, network_version, theta_jb, theta_jc);		
		case_point = jc_point;
		board_point = jb_point;
		context = ctx;		
	}
	
	public Functional_unit createResistor(String refDes, String description, Param resistance)
	{
		Functional_unit resistor = new Functional_unit(refDes, description, resistorFUUV);
		resistor.createTerminalsFromUsageView();
		resistor.addParam(new ParameterAssignment(resistance, resistanceModelParam));
		return resistor;
	}
	
	/**
	 * The thermal network will have three nodes and two resistors. All three nodes are included in the usage view.
	 * The nodes are named: 'board', 'junction', and 'case'.
	 * @return
	 */
	public Thermal_network_definition createThermalNetwork()
	{
		Thermal_network_definition thermal_network = new Thermal_network_definition(networkProduct);
		Thermal_functional_unit_network_node_definition board_node = new Thermal_functional_unit_network_node_definition("board", thermal_network);
		if(board_point != null) {
			board_node.location = board_point;
			board_node.location_context = context;
		}
		Thermal_functional_unit_network_node_definition junction_node = new Thermal_functional_unit_network_node_definition("junction", thermal_network);
		Thermal_functional_unit_network_node_definition case_node = new Thermal_functional_unit_network_node_definition("case", thermal_network);
		if(case_point != null) {
			case_node.location = case_point;
			case_node.location_context = context;
		}
		thermal_network.addNode(board_node);
		thermal_network.addNode(junction_node);
		thermal_network.addNode(case_node);
		thermal_network.createUsageView();
		Functional_unit junction_to_case = createResistor("theta_jc", "junction-to-case", junction_case_resistance);
		Functional_unit junction_to_board = createResistor("theta_jb", "junction-to-board", junction_board_resistance);
		Functional_unit_terminal junction_to_case_A = junction_to_case.getTerminalBySignalName("A");
		Functional_unit_terminal junction_to_case_B = junction_to_case.getTerminalBySignalName("B");
		Functional_unit_terminal junction_to_board_A = junction_to_board.getTerminalBySignalName("A");
		Functional_unit_terminal junction_to_board_B = junction_to_board.getTerminalBySignalName("B");
		case_node.addTerminal(junction_to_case_A);
		junction_node.addTerminal(junction_to_case_B);
		junction_node.addTerminal(junction_to_board_A);
		board_node.addTerminal(junction_to_board_B);
		return thermal_network;
	}
	
	/**
	 * standard SI unit for thermal resistance is degC/W
	 * @param r
	 * @return
	 */
	Param networkResistanceParameter(double r)
	{
		return new MeasureParamImpl("thermal resistance", r, 
				new DerivedSiUnit.ThermalResistanceUnit(
						new SiUnitTerm(SiBase.degC),
						new SiUnitTerm(SiBase.W, -1)));
	}
}
