package tests;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gui.RimplexController;
import gui.RimplexWindow;

class RimplexWindowTest {
	
	RimplexController controller;
	RimplexWindow window;

	@BeforeEach
	void setUp() throws Exception {
	  controller = new RimplexController();
	  window = new RimplexWindow(controller);
	  window.setVisible(true);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testHistoryPanel() {
	  JPanel history = window.getHistoryPanel();
	  JTextArea historyText = window.getHistoryDisplay();
	  assertNotNull(history, "Can't access history panel");
	  assertNotNull(historyText, "Can't access history panel");
	}

}
