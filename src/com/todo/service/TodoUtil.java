package com.todo.service;

import java.io.Writer;
import java.io.*;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, category,desc, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print(
				 "========== �׸� �߰�\n"
				+ "������ �Է��Ͻÿ� : ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("�̹� ���� ������ �ֽ��ϴ�");
			return;
		}
		System.out.print("ī�װ��� �Է��Ͻÿ� : ");
		category = sc.next().trim();
		sc.nextLine();
		System.out.print("������ �Է��Ͻÿ� : "); 
		desc = sc.nextLine().trim();
		System.out.print("�������ڸ� �Է��Ͻÿ� : ");
		due_date = sc.next().trim();
		
		TodoItem t = new TodoItem(title, category,desc,due_date);
		list.addItem(t);
		System.out.println("�߰��Ǿ����ϴ�."); 
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.print(
				"========== �׸� ����\n"
				+ "������ �׸��� ��ȣ�� ���ÿ� : "
				);
		int number = sc.nextInt();
		if (l.size()<number) {
			System.out.println("�׸� ��ȣ �߿� "+ number + "�� �����ϴ�");
			return;
		}
		TodoItem item = l.get(number-1);
		System.out.println((l.indexOf(item)+1)+". "+item.toString());
		System.out.print("�����Ͻðڽ��ϱ�?(y/n)");
		String question = sc.next().trim();
		if(question.charAt(0) == 'y') {
			l.deleteItem(item);
			System.out.println("�����Ǿ����ϴ�."); 
		}
			
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print(
				"========== �׸� ����\n"
				+ "������ �׸��� ��ȣ�� �Է��Ͻÿ� : ");
		int number = sc.nextInt();
		
		if (l.size()<number) {
			System.out.println("�׸� ��ȣ �߿� "+ number + "�� �����ϴ�");
			return;
		}
		TodoItem item = l.get(number-1);
		System.out.println((l.indexOf(item)+1)+". "+item.toString());

		System.out.print("���ο� ���� : ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("���� ������ ���� �׸��� �ֽ��ϴ�");
			return;
		}
		System.out.print("���ο� ī�װ� : ");
		String category = sc.next().trim();
		sc.nextLine();
		System.out.print("���ο� ���� : ");
		String new_description = sc.nextLine().trim();
		System.out.print("���ο� �������� : ");
		String due_date = sc.next().trim();
		
		l.deleteItem(item);
		TodoItem t = new TodoItem(new_title,category, new_description,due_date);
		l.addItem(t);
		System.out.println("�׸��� �����Ǿ����ϴ�.");
			
		
       
	}

	public static void listAll(TodoList l) {
		System.out.println("[��ü ���, �� "+ l.size() +"��]");
		for (TodoItem item : l.getList()) {
			System.out.println((l.indexOf(item)+1)+". "+item.toString());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			System.out.println("�׸� ������ "+filename+"�� �����߽��ϴ�!");
			
		}
		catch (FileNotFoundException e){
			System.out.println("������ ã�� �� �����ϴ�");
		}
		catch (IOException e){
			System.out.println("���� ���� �߻�!!");
		}
	}
	
	public static void find(TodoList l, String keyword) {
		for (TodoItem item : l.getList()) {
			if(item.toString().contains(keyword)) {
				System.out.println((l.indexOf(item)+1)+". "+item.toString());
			}
		}	
	}
	public static void find_cate(TodoList l, String keyword) {
		for (TodoItem item : l.getList()) {
			if(item.getCategory().contains(keyword)) {
				System.out.println((l.indexOf(item)+1)+". "+item.toString());
			}
		}	
	}
	
	public static void listCate(TodoList l) {
		int number = 0;
		HashSet<String> cateset = new HashSet<String>();
		for (TodoItem item : l.getList()) {
			cateset.add(item.getCategory());
		}
		for(Object obj : cateset.toArray()) {
			String category = (String) obj;
			System.out.print(category+"   ");
			number++;
		}
		System.out.println("\n�� "+number+"���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.");
	}
	
	
	public static void loadList(TodoList l, String filename) {
		try {
		BufferedReader br = new BufferedReader (new FileReader(filename));
		
		String oneLine;
		while((oneLine = br.readLine())!=null) {
			StringTokenizer st = new StringTokenizer(oneLine,"##");
			String title = st.nextToken();
			String category = st.nextToken();
			String desc = st.nextToken();
			String due_date = st.nextToken();
			String current_date = st.nextToken();
			
			TodoItem t = new TodoItem(title, category,desc,due_date);
			t.setCurrent_date(current_date);
			l.addItem(t);
		}
		br.close();
		System.out.println("�׸� ������ �ε��߽��ϴ�.");
		}
		catch (FileNotFoundException e){
			System.out.println("������ �����ϴ�.");
		}
		catch (IOException e){
			System.out.println("���� ���� �߻�!!");
		}
	
	}
}
