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
public static int no=10;	//Max number of URL in file
Label l[]=new Label[no];
Label status[]=new Label[no];
	public void init() {

		setLayout(new GridLayout(no+2,2));
		browse=new Button("Browse");
		add(browse);
		browse.addActionListener(this);
		add(new Label(""));
		
		Label x=new Label("URL",Label.CENTER);
		Label y=new Label("Status",Label.CENTER);
		
		x.setFont(new Font("Times New Roman",Font.BOLD,25));
		y.setFont(new Font("Times New Roman",Font.BOLD,25));
		add(x);
		add(y);
			for(int i=0;i<no;i++)
			{
				l[i]=new Label("-",Label.CENTER);
				l[i].setFont(new Font("SansSerif",Font.BOLD, 20));
				add(l[i]);
				status[i]=new Label("-",Label.CENTER);
				status[i].setFont(new Font("SansSerif",Font.BOLD, 20));
				add(status[i]);
			}
	}
	
public void actionPerformed(ActionEvent e) {
		if (e.getSource() == browse){
			
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
				l[i].setText(line);
				if(validator(line))
				{
					status[i].setText("UP");
					status[i].setForeground(Color.GREEN);
				}
				else
				{
					status[i].setText("Down");
					status[i].setForeground(Color.RED);
				}
			}
				}
				catch(Exception ex){
					l[0].setText("Check the type of file selected");
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
  URL hp = new URL("http://"+link);
  URLConnection hpCon = hp.openConnection();
  hpCon.connect();
  
} catch (Exception e) {
	//e.printStackTrace();
  isAlive= false;
}
return isAlive;
}
}