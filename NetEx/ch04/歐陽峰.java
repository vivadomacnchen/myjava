package com.ch04;

public class �ڶ��p extends �H {
	int ���O = 400;
	public �ڶ��p() {
		�m�W = "�ڶ��p";
		��O = 180;
		
	}
	public void �����\(){
		System.out.println(�m�W+"�o�X�K�K���n,�ϥX�����\");
	}
	public void �����\(�H  p){
		System.out.println(�m�W+"�o�X�K�K���n,�ϥX�����\���V"+p.�m�W);
		��O = ��O - 40;
		���O = ���O - 50;
		p.��O = p.��O - 80;
		System.out.println("  ���"+p.�m�W+"��O�Ѿl:"+p.��O);
	}
}
