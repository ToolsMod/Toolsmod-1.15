package de.whiletrue.toolsmod.util;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class AsyncJFileChooser{
	
	//Reference to the real file-chooser
	private JFileChooser chooser;
	
	//Thread for the async file-chooser
	private Thread thread = null;
	
	public AsyncJFileChooser() {
		this.chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	}
	
	/**
	 * Starts the async file request
	 * @param callback the callback method
	 * @param title the title of the chooser
	 */
	public void call(IFcCallback callback,String title) {
		//Kills any ongoing tasks
		this.stop();
		
		//Creates the thread
		this.thread=new Thread(()->{			
			//Gets the value
			int ret = this.chooser.showDialog(null, title);
			
			//Checks if a file got parsed
			if(ret == JFileChooser.APPROVE_OPTION) {
				//Checks if the thread got interrupted
				if(Thread.currentThread().isInterrupted())
					return;
				else
					callback.accept(this.chooser.getSelectedFile());
			}
		});
		this.thread.start();
	}
	
	/**
	 * Stops the async thread
	 */
	public void stop() {
		if(this.thread!=null) {
			this.thread.interrupt();
			this.thread=null;
		}
	}
	
	
	//Async callback
	@FunctionalInterface
	public interface IFcCallback{
		public void accept(File file);
	}
}
