package com.ch04;

public class �x�C�� extends �H {
	int ���O = 500;
	public �x�C��() {
		�m�W = "�x�C��";
		��O = 200;
		
	}
	public void ���s�Q�K�x(){
		System.out.println(�m�W+"�o�X�}�}�x��,�ϥX���s�Q�K�x");
	}
	public void ���s�Q�K�x(�H  p){
		System.out.println(�m�W+"�o�X�}�}�x��,�ϥX���s�Q�K�x���V"+p.�m�W);
		��O = ��O - 60;
		���O = ���O - 80;
		p.��O = p.��O - 120;
		System.out.println("  ���"+p.�m�W+"��O�Ѿl:"+p.��O);
	}
}
