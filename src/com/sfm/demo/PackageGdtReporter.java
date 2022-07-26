package com.sfm.demo;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ADatum;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ADimensional_location;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ADimensional_size;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AGeometric_tolerance;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.APackage;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AShape_aspect;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CPackage;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EDatum;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EDimensional_location;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EDimensional_size;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EGeometric_tolerance;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EPackage;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_aspect;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_representation;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiIterator;
import jsdai.lang.SdaiModel;

import com.sfm.ap210.jsdai.MIMops;
import com.sfm.ap210.jsdai.MIMopsFactory;
import com.sfm.ap210.jsdai.MIMpackageQueriesWithGDT;
import com.sfm.ap210.jsdai.MIMpackageQueriesWithGDTImpl;
import com.sfm.ap210.jsdai.MIMparamQueries;
import com.sfm.ap210.jsdai.MIMparamQueriesImpl;
import com.sfm.ap210.jsdai.MIMqueries;
import com.sfm.ap210.jsdai.MIMqueryFactory;
import com.sfm.ap210.jsdai.gdt.Datum;
import com.sfm.ap210.jsdai.gdt.DimensionalLocation;
import com.sfm.ap210.jsdai.gdt.DimensionalSize;
import com.sfm.ap210.jsdai.gdt.GeometricTolerance;

public class PackageGdtReporter {
	SdaiModel model;
	EPackage e_package = null;
	EShape_representation e_package_3d_sr = null;
	int jsdaiTRUE = 2;
	int jsdaiFALSE = 1;
	MIMqueries mimQ = null;
	MIMops ops = null;
	MIMparamQueries paramQ = null;
	MIMpackageQueriesWithGDT packageQ = null;
	
		public PackageGdtReporter(SdaiModel m) throws SdaiException
		{
			model = m;
			ops = MIMopsFactory.getImpl(model);
			//mimQ = MIMqueryFactory.getImpl(model, ops);
			mimQ = MIMqueryFactory.getMapImpl(model, ops);
			paramQ = new MIMparamQueriesImpl(mimQ, ops);
			packageQ = new MIMpackageQueriesWithGDTImpl(mimQ, ops, paramQ);
		
			ADatum a_datum = packageQ.getDatumsInModel();
			reportDatums(a_datum);
			
			ADimensional_size a_dimensional_size = packageQ.getAllDimensionalSizeInModel();
			reportDimensionalSize(a_dimensional_size);
			
			ADimensional_location a_dimensional_location = packageQ.getAllDimensionalLocationInModel();
			reportDimensionalLocation(a_dimensional_location);
			
			AGeometric_tolerance a_geometric_tolerance = packageQ.getAllGeometricToleranceInModel();
			reportGeometricTolerance(a_geometric_tolerance);
			
			e_package = getPackage();
			e_package_3d_sr = packageQ.get3dShapeRepresentationOfPackage(e_package);
		}
		
		EPackage getPackage() throws SdaiException
		{
			APackage a_package = (APackage)	model.getExactInstances(CPackage.class);
			if (a_package.getMemberCount() != 1)
			{
				System.out.println("Unable to find exactly 1 package....");
				return null;
			}
			return (EPackage) a_package.getByIndex(1);
		}
		
		void reportDatum(EDatum e_datum) throws SdaiException
		{
			Datum d = packageQ.readDatum(e_datum);
			//System.out.println("Datum '"+e_datum.getIdentification(null)+"' : "+e_datum.getPersistentLabel());
			System.out.println(d.toString());
		}
		
		void reportDatums(ADatum a_datum) throws SdaiException
		{
			SdaiIterator it_d = a_datum.createIterator();
			EDatum e_datum = null;
			while (it_d.next())
			{
				e_datum = a_datum.getCurrentMember(it_d);
				reportDatum(e_datum);
			}
		}
		
		void reportDimensionalSize(ADimensional_size a_ds) throws SdaiException
		{
			SdaiIterator it = a_ds.createIterator();
			EDimensional_size e_ds = null;
			while (it.next())
			{
				e_ds = a_ds.getCurrentMember(it);
				reportDimensionalSize(e_ds);
			}
		}
		
		void reportDimensionalSize(EDimensional_size e_ds) throws SdaiException
		{
			DimensionalSize ds = packageQ.readDimensionalSize(e_ds);
			System.out.println(ds.toString());
			/*
			ToleranceLengthValue v = packageQ.getValueOfDimensionalSize(e_ds);
			System.out.println("Dimensional_size '"+v.toString()+"' : "+e_ds.getPersistentLabel());
			EShape_aspect e_applies_to_sa = (EShape_aspect) e_ds.getApplies_to(null);
			System.out.println("Applies to : "+e_applies_to_sa);
			AShape_aspect a_composing_sa = packageQ.getAllComposingShapeAspects(e_applies_to_sa);
			reportSA(a_composing_sa);
			Set<FaceReference> faceRefs = packageQ.getFaceReferencesOfShapeAspect(e_applies_to_sa);
			for (FaceReference face : faceRefs)
			{
				System.out.println(face.toString());
			}
			*/
		}
		
		void reportDimensionalLocation(ADimensional_location a_ds) throws SdaiException
		{
			SdaiIterator it = a_ds.createIterator();
			EDimensional_location e_ds = null;
			while (it.next())
			{
				e_ds = a_ds.getCurrentMember(it);
				reportDimensionalLocation(e_ds);
			}
		}
		
		void reportDimensionalLocation(EDimensional_location e_dl) throws SdaiException
		{
			DimensionalLocation dl = packageQ.readDimensionalLocation(e_dl);
			System.out.println(dl.toString());
			/*
			ToleranceLengthValue v = packageQ.getValueOfDimensionalLocation(e_dl);
			System.out.println("Dimensional_location '"+v.toString()+"' : "+e_dl.getPersistentLabel());
			EShape_aspect e_sa1 = e_dl.getRelating_shape_aspect(null);
			EShape_aspect e_sa2 = e_dl.getRelated_shape_aspect(null);
			System.out.println("Feature 1: "+e_sa1);
			Set<FaceReference> faceRefs1 = packageQ.getFaceReferencesOfShapeAspect(e_sa1);
			for (FaceReference face : faceRefs1)
			{
				System.out.println(face.toString());
			}
			System.out.println("Feature 2: "+e_sa1);
			Set<FaceReference> faceRefs2 = packageQ.getFaceReferencesOfShapeAspect(e_sa2);
			for (FaceReference face : faceRefs2)
			{
				System.out.println(face.toString());
			}
			*/
		}
		
		void reportGeometricTolerance(AGeometric_tolerance a_gt) throws SdaiException
		{
			SdaiIterator it = a_gt.createIterator();
			EGeometric_tolerance e_gt = null;
			while (it.next())
			{
				e_gt = a_gt.getCurrentMember(it);
				reportGeometricTolerance(e_gt);
			}
		}
		
		void reportGeometricTolerance(EGeometric_tolerance e_gt) throws SdaiException
		{
			GeometricTolerance tol = packageQ.readGeometricTolerance(e_gt);
			System.out.println(tol.toString());
			/*
			System.out.println("Geometric_tolerance : "+e_gt.getPersistentLabel());
			EShape_aspect e_toleranced_sa = (EShape_aspect) e_gt.getToleranced_shape_aspect(null);
			System.out.println("Toleranced shape_aspect : "+e_toleranced_sa);
			AShape_aspect a_composing_sa = packageQ.getAllComposingShapeAspects(e_toleranced_sa);
			reportSA(a_composing_sa);
			Set<FaceReference> faceRefs = packageQ.getFaceReferencesOfShapeAspect(e_toleranced_sa);
			for (FaceReference face : faceRefs)
			{
				System.out.println(face.toString());
			}
			*/
		}
		
		void reportSA(AShape_aspect a_sa) throws SdaiException
		{
			SdaiIterator it = a_sa.createIterator();
			while (it.next())
			{
				EShape_aspect e_sa = a_sa.getCurrentMember(it);
				System.out.println(e_sa);
			}
		}
		
}
