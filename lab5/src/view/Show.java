package view;



import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import clazz.ClassRoom;
import clazz.Course;
import clazz.CourseForm;
import clazz.Teacher;
import clazz.Class;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

import java.util.Set;

import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class Show extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private static LinkedList<Class> classList = new LinkedList<>();
    private static LinkedList<ClassRoom> classroomList = new LinkedList<>();
    private static LinkedList<Course> courseList = new LinkedList<>();
    private static LinkedList<Teacher> teacherList = new LinkedList<>();
    private static Set<String> preCouse = new HashSet<>();
    private static Set<String> having_preCourse = new HashSet<>();
    private static LinkedList<String> temList1;
    private static LinkedList<String> temList2;
    private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Show frame = new Show();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Show() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Label1 = new JLabel("�ſ�ϵͳ");
		Label1.setFont(new Font("���ֹ������Σ������ã�������", Font.PLAIN, 38));
		Label1.setBounds(148, 27, 165, 72);
		contentPane.add(Label1);
		
		try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("Class.txt")), "GBK"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] result = line.split(" ");
                classList.add(new Class(Integer.parseInt(result[0]), result[1], Integer.parseInt(result[2])));
            }
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("ClassRoom.txt")), "GBK"));
            while ((line = br.readLine()) != null) {
                String[] result = line.split(" ");
                classroomList.add(new ClassRoom(Integer.parseInt(result[0]), result[1], Integer.parseInt(result[2])));
            }
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("Course.txt")), "GBK"));
            while ((line = br.readLine()) != null) {
                String[] result = line.split(" ");
                courseList.add(new Course(Integer.parseInt(result[0]), result[1], Integer.parseInt(result[2])));
                for (int i = 3; i < result.length; i++) {
                    courseList.getLast().precourse.add(result[i]);
                    preCouse.add(result[i]);
                    having_preCourse.add(result[1]);
                }
            }
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("Teacher.txt")), "GBK"));
            while ((line = br.readLine()) != null) {
                String[] result = line.split(" ");
                teacherList.add(new Teacher(Integer.parseInt(result[0]), result[1]));
                for (int i = 2; i < result.length; i++) {
                    if (result[i].length() == 1 && result[i].charAt(0) <= 'z' && result[i].charAt(0) >= 'a') {
                        teacherList.getLast().getSq().getList().remove(result[i].charAt(0) - 'a');
                    } else
                        teacherList.getLast().teachCourse.add(result[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

		
		JButton Button1 = new JButton("��ʼ�ſ�");
		Button1.setBounds(100, 100, 100, 27);
		contentPane.add(Button1);
		
		JButton Button2 = new JButton("ָ���꼶�α�");
		Button2.setBounds(210, 151, 140, 27);
		contentPane.add(Button2);
		
		JButton Button3 = new JButton("ָ����ʦ�α�");
		Button3.setBounds(211, 207, 139, 27);
		contentPane.add(Button3);
		
		JButton Button4 = new JButton("�����꼶�α�");
		Button4.setBounds(102, 261, 248, 27);
		contentPane.add(Button4);
		
		JButton Button5 = new JButton("�˳�ϵͳ");
		Button5.setBounds(237, 100, 113, 27);
		contentPane.add(Button5);
		
		JSpinner spinner1 = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
		spinner1.setBounds(100, 152, 80, 24);
		contentPane.add(spinner1);
		
		textField = new JTextField();
		textField.setBounds(100, 208, 86, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		Button1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 System.out.println("�ſ��С�����");
                 Arranging();
                 System.out.println("�ſγɹ���");
                 JOptionPane.showMessageDialog(contentPane, "�ſγɹ�", "�ɹ�", 1);
			}
		});
		
		Button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int number = (int) spinner1.getValue();
				showClass(number);
			}
		});
		
		Button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = textField.getText();
				showTeacher(name);
			}
		});
		
		Button4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (Class x : classList) {
					ShowAll(x.observed, x.getName() + "�İ༶�α�");
                }
			}
		});
		
		Button5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	}
	
	 private static void showClass(int grade) {
	        for (Class x : classList) {
	            if (x.getGrade() == grade) {
	            	ShowAll(x.observed, x.getName() + "�İ༶�α�");
	                return;
	            }
	        }

	    }

	    private static void showTeacher(String teachername) {
	        for (Teacher x : teacherList) {
	        	System.out.println(x.getName());
	            if (x.getName().equals(teachername) ) {
	            	ShowAll(x.observed, x.getName() + "�Ŀα�");
	                //return;
	            }
	        }
	    }

	
	 private static void ShowAll(String[] list, String string) {
	        JFrame frame = new JFrame(string);
	        JTable table = new JTable(new CourseForm(list));
	    	//���ñ����ݾ�����ʾ
			DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
			cr.setHorizontalAlignment(JLabel.CENTER);
			table.setDefaultRenderer( Object.class, cr);
	        table.setRowHeight(120);
	        JScrollPane pane = new JScrollPane(table);
	        frame.getContentPane().add(pane);
	        //frame.pack();
	        frame.setSize(1200,600);
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.setVisible(true);
	 }

	 private static void Arranging() {
	        for (Course cou : courseList) {
	            Teacher tea = null;
	            Class cla = null;
	            if (preCouse.contains(cou.getName())) {
	                for (Class x : classList) {
	                    if (x.getGrade() == 1) {
	                        cla = x;
	                        break;
	                    }
	                }
	                for (Teacher x : teacherList) {
	                    if (x.teachCourse.contains(cou.getName())) {
	                        tea = x;
	                        break;
	                    }
	                }
	                
	                order(tea, cla, cou);
	          
	            } else if (having_preCourse.contains(cou.getName())) {
	                int max = 0;
	                for (Class x : classList) {
	                    if (x.getSq().getList().size() > max && x.getGrade() != 1) {
	                        cla = x;
	                        max = x.getSq().getList().size();
	                    }
	                }
	                for (Teacher x : teacherList) {
	                    if (x.teachCourse.contains(cou.getName())) {
	                        tea = x;
	                        break;
	                    }
	                }
	                order(tea, cla, cou);
	            } else {
	                int max = 0;
	                for (Class x : classList) {
	                    if (x.getSq().getList().size() > max) {
	                        cla = x;
	                        max = x.getSq().getList().size();
	                    }
	                }
	                for (Teacher x : teacherList) {
	                    if (x.teachCourse.contains(cou.getName())) {
	                        tea = x;
	                        break;
	                    }
	                }
	                order(tea, cla, cou);
	            }
	        }
	    }
	 
	    // ��LinkedList<String>�����ȡ��timesPerWeek��Ԫ����ɵ�LinkedList<String>
	    private static LinkedList<String> randList(LinkedList<String> list, int timesPerWeek) {
	        LinkedList<String> subList = new LinkedList<String>();
	        Random rand = new Random();
	        int j = 0;
	        while (true) {
	            String m = list.get(rand.nextInt(list.size()));

	            if (!subList.contains(m)) {
	                subList.add(m);
	                j++;
	            }
	            if (j == timesPerWeek) {
	                break;
	            }
	        }
	        return subList;
	    }
	 
	  @SuppressWarnings("unchecked")
	private static void order(Teacher te, Class cl, Course co) {
	        temList1 = (LinkedList<String>) (te.getSq().getList().clone());
	        te.getSq().getList().retainAll(cl.getSq().getList());
	        if (te.getSq().getList().size() < co.getTimesWeek()) {
	        	JOptionPane.showMessageDialog(null, "��ʦ��ͬѧ����������ʱ�䲻�㰲��", "����ʧ��", 1);
	            System.out.println("��ʦ��ͬѧ����������ʱ�䲻�㰲��" + co.getName());
	        }
	        temList2 = randList(te.getSq().getList(), co.getTimesWeek());
	        cl.getSq().getList().removeAll(temList2);
	        te.getSq().setList(temList1);
	        te.getSq().getList().removeAll(temList2);
	        //System.out.println(cl.getSq().getList().size());
	        for (String x : temList2) {
	            int max=1000;
	            ClassRoom selectRoom=null;
	            for (ClassRoom y: classroomList) {
	                if(y.getSq().getList().contains(x)&&y.getCapacity()-cl.getSum()<max
	                		&&y.getCapacity()-cl.getSum()>=0) {
	                    max = y.getCapacity() - cl.getSum();
	                    selectRoom=y;
	                }
	            }
	            if(selectRoom!=null){
	                selectRoom.getSq().getList().remove(x);
	            }
	            else{
	                System.out.println("������Դ������");
	                JOptionPane.showMessageDialog(null, "������Դ������", "��ʾ", 1);
	                return;
	            }
	            te.observed[x.charAt(0) - 'a'] = co.getName()+" "+cl.getName()+" "+selectRoom.getName();
	            cl.observed[x.charAt(0) - 'a'] = co.getName()+" "+te.getName()+" "+selectRoom.getName();
	        }
	    }
}
