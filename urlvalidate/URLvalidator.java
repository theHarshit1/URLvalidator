import java.net.URL;
import java.net.URLConnection;
import java.io.InputStream;
import java.io.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class URLvalidator extends Applet implements ActionListener{

private JFileChooser fileChooser;
private FileSystemView fsv;

Button browse;
Label l[]=new Label[10];
	public void init() {
		setLayout(new GridLayout(11,1));
		browse=new Button("Browse");
		add(browse);
		browse.addActionListener(this);
			for(int i=0;i<10;i++)
			{
				
				l[i]=new Label("URl");
				add(l[i]);
			}
	}
	
public void actionPerformed(ActionEvent e) {
		if (e.getSource() == browse){
			int arrlen = 10000;
			byte[] infile = new byte[arrlen];
			Frame parent = new Frame();
			FileDialog fd = new FileDialog(parent, "Please choose a file:",
			    FileDialog.LOAD);
			fd.show();
			String selectedItem = fd.getFile();
			if (selectedItem == null) {
				l[0].setText("No file selected");
			} else {
				
				try{
					BufferedReader br = new BufferedReader(new FileReader(fd.getDirectory() + File.separator + fd.getFile()));
				int i=0;
				try{
			for( String line ;(line = br.readLine()) != null;i++ ) {
				l[i].setFont(new Font("SansSerif", Font.BOLD, 18));
				if(validator(line))
					l[i].setText(line+"\t:: Up\n");
				else
					l[i].setText(line+"\t:: Down\n");
			}
				}
				catch(Exception ex){
					
				}
				}
			catch(FileNotFoundException ex)
			{
				l[0].setText("File Not found"+ex);
			}
			}
		}
	}
	
public boolean validator(String link){
	boolean isAlive = true;
try {
  URL hp = new URL(link);
  URLConnection hpCon = hp.openConnection();
  hpCon.connect();
  
} catch (Exception e) {
	//e.printStackTrace();
  isAlive= false;
}
return isAlive;
}
}