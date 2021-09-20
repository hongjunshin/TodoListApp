package com.todo.service;

import java.io.Writer;
import java.io.*;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print(
				 "========== �׸� �߰�\n"
				+ "������ �Է��Ͻÿ� : ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("�̹� ���� ������ �ֽ��ϴ�");
			return;
		}
		sc.nextLine();
		System.out.print("������ �Է��Ͻÿ� : "); 
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("�߰��Ǿ����ϴ�."); 
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.print(
				"========== �׸� ����\n"
				+ "������ �׸��� ������ ���ÿ� : "
				);
		String title = sc.next();
		if (!(l.isDuplicate(title))) {
			System.out.println("�׸� �߿� "+ title + "�� �����ϴ�");
			return;
		}
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("�����Ǿ����ϴ�."); 
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print(
				"========== �׸� ����\n"
				+ "������ �׸��� ������ �Է��Ͻÿ� : ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("�׸� �߿� "+ title + "�� �����ϴ�");
			return;
		}

		System.out.print("���ο� ���� : ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("���� ������ ���� �׸��� �ֽ��ϴ�");
			return;
		}
		sc.nextLine();
		System.out.print("���ο� ���� : ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("�׸��� �����Ǿ����ϴ�.");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("[��ü ���]");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
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
		
	
	
	public static void loadList(TodoList l, String filename) {
		try {
		BufferedReader br = new BufferedReader (new FileReader(filename));
		
		String oneLine;
		while((oneLine = br.readLine())!=null) {
			StringTokenizer st = new StringTokenizer(oneLine,"##");
			String title = st.nextToken();
			String desc = st.nextToken();
			String current_date = st.nextToken();
			
			TodoItem t = new TodoItem(title, desc, current_date);
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
