package mmn14targil2;            // omer golan
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;





public class Reminder extends JFrame
{
	private final JComboBox<Integer> dayCombo;
	private final JComboBox<String> monthCombo;
	private final JComboBox<Integer> yearCombo;
	private JButton save;
	private JButton getDate;
	private JButton exit;
	BorderLayout layout;
	JPanel panel1;
	JPanel panel2;
	JTextArea textArea;
	Hashtable<Date, String> reminder;
	int _day;
	String _month;
	int _year;
	Date date;
    File f ;
   /************ Constructor **************************************************************************/
	public Reminder()
	/*************************************************************************************************/
	{
		  super( " Reminder");
		  layout = new BorderLayout();
		  setLayout(layout);
		  final Integer[] day= new Integer [31];
		  int i;
		  for( i=0;i<31;i++)
		  {
			  day[i]=i+1 ;
		  }
		  
		 
		  
		  final String month[] = {"January","February" ,"March","April","May","June","July","Augus","September","October","November","December" };                    
		  final Integer year[] = {2010,2011,2012,2013,2014,2015,2015,2016,2017,2018,2019};	

		dayCombo = new JComboBox<Integer>(day);
		monthCombo = new JComboBox<String>(month);
		yearCombo = new JComboBox<Integer>(year);
		
		dayCombo.setMaximumRowCount(31);
		monthCombo.setMaximumRowCount(12);
		yearCombo.setMaximumRowCount(20);
		
		JTextField text =  new JTextField();
	    save = new  JButton ( " save");
	    getDate = new  JButton ( " getDate");
	    exit = new JButton ( " Exit");
	    

	    panel1 = new JPanel();
	    panel2 = new JPanel();

	    add(panel1,BorderLayout.NORTH);
	    add(panel2,BorderLayout.SOUTH);

	    panel1.add(dayCombo);
	    panel1.add(monthCombo);
	    panel1.add(yearCombo);
	    
	    panel2.add(save);
	    panel2.add(getDate);
	    panel2.add(exit);

	    
	    textArea = new JTextArea(5, 20);
	    add(textArea,BorderLayout.CENTER);
	    reminder = new Hashtable<Date, String>();
	    DatListener handler = new DatListener();
	    
	    
	    dayCombo.addItemListener(handler);
	    monthCombo.addItemListener(handler);
	    yearCombo.addItemListener(handler);
	    
	    ButtonsHandler handler2 = new ButtonsHandler();
	    save.addActionListener(handler2);
	    getDate.addActionListener(handler2);
	    exit.addActionListener(handler2);
	}
	
	/******************** Handler the JCombobox ***********************************************************/
	   private  class DatListener implements ItemListener
    /*****************************************************************************************************/
	    {
	    	public void itemStateChanged (ItemEvent event)
	    	{
	    		int day=0;
	    		String month=null;
	    		int year=0;
	    		if(event.getSource()==dayCombo)
				{
	    			if(event.getStateChange() == ItemEvent.SELECTED)
	    			{
						 Integer day1 = (Integer)event.getItem();
						  _day=(int)day1;
	    			}
				}
	    		if(event.getSource()==monthCombo)
				{
	    			if(event.getStateChange() == ItemEvent.SELECTED)
	    			{
						  _month = (String)event.getItem();
	    			}
				}
	    		if(event.getSource()==yearCombo)
				{
	    			if(event.getStateChange() == ItemEvent.SELECTED)
	    			{
						 Integer year1 = (Integer)event.getItem();
						  _year = (int)year1;
	    			}
				}
	    	
	    	}
		
	    }
	   
	  /************************ Handler the Buttons ************************************************************/
		private class ButtonsHandler implements ActionListener
	 /*********************************************************************************************************/
		{
			
			public void actionPerformed(ActionEvent event)
			{
	   		if(event.getSource()==save)                              // Button save
					{
	   					 date = new Date (_day,_month,_year);
	   				     String s=textArea.getText();
	   					 reminder.put(date, s);
	   					 
	   			 try {
	   					 
	   				 
	   				     f = new File("rem.txt");
	   					f.createNewFile();
	   					
	   					 FileOutputStream fout = new FileOutputStream(f);
	   					 ObjectOutputStream oos = new ObjectOutputStream(fout);
	   		
	   					 oos.writeObject(reminder);
	   					 oos.close();
	   					 
	   					 
	   				  }
	   				   catch( IOException ioException)
	   				   {
	   						System.err.println("Error opening file . Terminating.");
	   						System.exit(1);
	   					}
	   				 
	   					 
	   					 
	   					
					}
		    		if(event.getSource()==getDate)                       // Button getDate
					{
		    			
		    		try 
		    		{
		   						
		   			     FileInputStream fis = new FileInputStream(f);
		   		         ObjectInputStream ois = new ObjectInputStream(fis);
		   		     
		   		         Hashtable reminder = (Hashtable) ois.readObject();
		    			
		   		         date = new Date (_day,_month,_year);
		    			 String  s =(String)reminder.get(date);
		    			 textArea.setText(s);
		    		 }
		    		 		catch( IOException ioException)
		    				{
		    					System.err.println("Error opening file . Terminating.");
		    					System.exit(1);
		    				} catch (ClassNotFoundException e) 
		    		        {
								e.printStackTrace();
							}	 
		    			 
		    			 
		    			 
					}
		    		if(event.getSource()==exit)                            // Button exit
					{
		    			System.exit(0);
					}
			}
		}

}

