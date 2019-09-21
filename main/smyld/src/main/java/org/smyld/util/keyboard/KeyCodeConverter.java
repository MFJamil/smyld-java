package org.smyld.util.keyboard;

import java.awt.event.KeyEvent;

public class KeyCodeConverter {
	public KeyCodeConverter() {
	}

	public static int[] convertToKeyCodes(String text) {
		int[] result = new int[text.length()];
		for (int i = 0; i < text.length(); i++) {
			result[i] = getKeyCode(text.charAt(i));
		}

		return result;
	}

	private static int getKeyCode(char letter) {
		switch (letter) {
		case 'A':
		case 'a':
			return KeyEvent.VK_A;
		case 'B':
		case 'b':
			return KeyEvent.VK_B;
		case 'C':
		case 'c':
			return KeyEvent.VK_C;
		case 'D':
		case 'd':
			return KeyEvent.VK_D;
		case 'E':
		case 'e':
			return KeyEvent.VK_E;
		case 'F':
		case 'f':
			return KeyEvent.VK_F;
		case 'G':
		case 'g':
			return KeyEvent.VK_G;
		case 'H':
		case 'h':
			return KeyEvent.VK_H;
		case 'I':
		case 'i':
			return KeyEvent.VK_I;
		case 'J':
		case 'j':
			return KeyEvent.VK_J;
		case 'K':
		case 'k':
			return KeyEvent.VK_K;
		case 'L':
		case 'l':
			return KeyEvent.VK_L;
		case 'M':
		case 'm':
			return KeyEvent.VK_M;
		case 'N':
		case 'n':
			return KeyEvent.VK_N;
		case 'O':
		case 'o':
			return KeyEvent.VK_O;
		case 'P':
		case 'p':
			return KeyEvent.VK_P;
		case 'Q':
		case 'q':
			return KeyEvent.VK_Q;
		case 'R':
		case 'r':
			return KeyEvent.VK_R;
		case 'S':
		case 's':
			return KeyEvent.VK_S;
		case 'T':
		case 't':
			return KeyEvent.VK_T;
		case 'U':
		case 'u':
			return KeyEvent.VK_U;
		case 'V':
		case 'v':
			return KeyEvent.VK_V;
		case 'W':
		case 'w':
			return KeyEvent.VK_W;
		case 'X':
		case 'x':
			return KeyEvent.VK_X;
		case 'Y':
		case 'y':
			return KeyEvent.VK_Y;
		case 'Z':
		case 'z':
			return KeyEvent.VK_Z;
		case '0':
			return KeyEvent.VK_0;
		case '1':
			return KeyEvent.VK_1;
		case '2':
			return KeyEvent.VK_2;
		case '3':
			return KeyEvent.VK_3;
		case '4':
			return KeyEvent.VK_4;
		case '5':
			return KeyEvent.VK_5;
		case '6':
			return KeyEvent.VK_6;
		case '7':
			return KeyEvent.VK_7;
		case '8':
			return KeyEvent.VK_8;
		case '9':
			return KeyEvent.VK_9;
		case ' ':
			return KeyEvent.VK_SPACE;
		case ',':
			return KeyEvent.VK_COMMA;
		case '@':
			return KeyEvent.VK_AT;
		case '\\':
			return KeyEvent.VK_BACK_SLASH;
		case '/':
			return KeyEvent.VK_SLASH;
		case '(':
			return KeyEvent.VK_LEFT_PARENTHESIS;
		case ')':
			return KeyEvent.VK_RIGHT_PARENTHESIS;

		}
		return KeyEvent.VK_SPACE;

	}

}
