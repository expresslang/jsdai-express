package com.sfm.ap210.jsdai.utils;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import jsdai.lang.SdaiException;
import jsdai.lang.SdaiModel;
import jsdai.lang.SdaiRepository;
import jsdai.lang.SdaiSession;

/**
 * This is a utility script to dump a GraphML representation of the
 * connectivity graph of a STEP Part21 file. Running the application will
 * present the user with a file prompt for a .stp file. A .stp.graphml 
 * will be created alongside the .stp file for the dumped connectivity 
 * graph data. The user is then free to load and examine the graph data in 
 * any capable graphml reader application.
 */
public class GraphDump {
	
	//simple utility to dump a GraphML representation
	//of a Part21 STEP file
	public static void main(String[] args) throws SdaiException, IOException {
		SdaiSession session = null;
		try {
			session = SdaiUtils.newRWSession();
			File src = openPrompt();
			if(src == null)
				return;
			File dst = new File(src.getAbsolutePath()+".graphml");
			SdaiRepository repo = SdaiUtils.newRepo(session, "dump");
			session.importClearTextEncoding(src.getAbsolutePath(), repo);
			SdaiModel model = SdaiUtils.Sequence(SdaiModel.class,repo.getModels()).get(0);
			GraphMLWriter writer = new GraphMLWriter(model);
			writer.write(dst);
			JOptionPane.showMessageDialog(null, 
					String.format("Dumped to: %s", dst.getAbsolutePath()), 
					"Dump Complete", 
					JOptionPane.INFORMATION_MESSAGE);
		} catch(Throwable t) {
			JOptionPane.showMessageDialog(null, 
					t.getMessage(), 
					"Error", 
					JOptionPane.ERROR_MESSAGE);
		}finally {
			if(session != null)
				SdaiUtils.close(session);
		}		
	}
	
	static File openPrompt() {
		JFileChooser fileChooser = new JFileChooser();			
		int retCode = JFileChooser.CANCEL_OPTION;
		fileChooser.setDialogTitle("Choose .stp file for GraphML dump");
		retCode = fileChooser.showOpenDialog(null);
		if(retCode != JFileChooser.APPROVE_OPTION)
			return null;
		else
			return fileChooser.getSelectedFile();
	}		

}
