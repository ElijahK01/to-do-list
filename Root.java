package projectMain;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Root {

	public static void main(String[] args) {
		
		windowSetup();
		
	}
	public static void windowSetup() {
		JFrame f = new JFrame(); // creating frame
		
		JLabel addLabel = new JLabel("Add Event:"); // creating labels and textfields
		JTextField eventName = new JTextField("Event Name");
		JTextField timeDue = new JTextField("Time Due");
		JTextField notes = new JTextField("Notes");
		
		String category[] = {"Category0","Category1","Category2","Category3","Category4","Category5","Category6","Category7"}; // creating drop boxes
		String dayDue[] = {"Monday","Tuesday","Wednsday","Thursday","Friday","Weekend"};
		JComboBox<?> eventCategory = new JComboBox<Object>(category);
		JComboBox<?> due = new JComboBox<Object>(dayDue);
		
		
		JButton add = new JButton("Add Event"); // defining buttons
		add.setBackground(Color.green);
		
		JButton clear = new JButton("Clear All");
		clear.setBackground(Color.red);
		
		JButton save = new JButton("Save");
		save.setBackground(Color.blue);
		
		JButton restore = new JButton("Restore");
		restore.setBackground(Color.yellow);
		
		
		addLabel.setBounds(2, 7, 75, 10); // setting component boundaries
		eventName.setBounds(75, 0, 100, 25);
		eventCategory.setBounds(175, 0, 100, 25);
		due.setBounds(175, 25, 100, 25);
		timeDue.setBounds(75, 25, 100, 25);
		notes.setBounds(75, 50, 200, 25);
		add.setBounds(175, 75, 100, 25);
		clear.setBounds(275, 0, 100, 25);
		save.setBounds(275, 25, 100, 25);
		restore.setBounds(275, 50, 100, 25);
		
		f.add(addLabel); // adding sections to frame
		f.add(eventName);
		f.add(eventCategory);
		f.add(due);
		f.add(timeDue);
		f.add(notes);
		f.add(add);
		f.add(clear);
		f.add(save);
		f.add(restore);
		
		JPanel p1 = new JPanel(); // information panels
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();
		
		List<String> data = new ArrayList<String>();
		
		add.addActionListener(new ActionListener() { // action listener for the add event button 
			@Override
			public void actionPerformed(ActionEvent e) { // performed when add event is clicked
				String name = eventName.getText();
				String time = timeDue.getText();
				String day = (String) due.getSelectedItem();
				String subject = (String)eventCategory.getSelectedItem();
				String note = notes.getText();
				String info = AddEvent.addEvent(name, time, day, subject, note);
				
				data.add(info+note);
				
				switch(day) { // adds event to correct day
				case "Monday":
					p1.add(new JLabel(info+note));
					break;
				case "Tuesday":
					p2.add(new JLabel(info+note));
					break;
				case "Wednsday":
					p3.add(new JLabel(info+note));
					break;
				case "Thursday":
					p4.add(new JLabel(info+note));
					break;
				case "Friday":
					p5.add(new JLabel(info+note));
					break;
				case "Weekend":
					p6.add(new JLabel(info+note));
					break;
				}
			}
		});
		
		clear.addActionListener(new ActionListener() { // clear button listener
			@Override
			public void actionPerformed(ActionEvent e) { // performed when clear is clicked
				p1.removeAll();
				p1.validate();
				p2.removeAll();
				p2.validate();
				p3.removeAll();
				p3.validate();
				p4.removeAll();
				p4.validate();
				p5.removeAll();
				p5.validate();
				p6.removeAll();
				p6.validate();
				data.clear();
			}
		});
		
		save.addActionListener(new ActionListener() { // save function and listener
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					SaveFile.saveFile(data);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		restore.addActionListener(new ActionListener() { //restore function and listener
			@Override
			public void actionPerformed(ActionEvent e) {
				String fileInfo;
				try {
					fileInfo = SaveFile.getFile(); // references another class
					for (String item: fileInfo.split(",")) {
						item.trim();
						item = item.replaceAll("[\\[\\](){}]","");
						String day = FindDay.findDay(item);
						System.out.println(day);
						String info = item;
						switch(day) { // applies restored info to correct day
						case "Monday":
							p1.add(new JLabel(info));
							break;
						case "Tuesday":
							p2.add(new JLabel(info));
							break;
						case "Wednsday":
							p3.add(new JLabel(info));
							break;
						case "Thursday":
							p4.add(new JLabel(info));
							break;
						case "Friday":
							p5.add(new JLabel(info));
							break;
						case "Weekend":
							p6.add(new JLabel(info));
							break;
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		JTabbedPane week = new JTabbedPane(); // creates week tabs
		week.setBounds(0,100,400,600);
		
		week.add("Mon",p1);
		week.add("Tues",p2);
		week.add("Wed",p3);
		week.add("Thurs",p4);
		week.add("Fri",p5);
		week.add("Wkd",p6);
		
		f.add(week); // adds week tabs to window
		
		f.setSize(400,600);
		f.setLayout(null);
		f.setTitle("To-Do List");
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
