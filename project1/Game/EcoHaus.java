// James Cui
// Date: 4/24/2023
// EcoHaus.java
// This is the game project.
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class EcoHaus
{
	public static void main(String[] args)
	{
		EcoHaus eH = new EcoHaus();
		eH.setInterface();
	}

	public void setInterface()
	{
		JFrame eHframe = new JFrame();
		eHframe.setSize(900, 700);
		eHframe.setLocation(0,0);
		eHframe.setResizable(true);
		eHframe.setDefaultCloseOperation(eHframe.EXIT_ON_CLOSE);
		EcoHausHolder eHH = new EcoHausHolder();
		eHframe.getContentPane().add(eHH);
		eHframe.setVisible(true);
	}
}

	class EcoHausHolder extends JPanel
	{
		private NavPage nP;
		public EcoHausHolder()
		{
			CardLayout eHHcards = new CardLayout();
			setLayout(eHHcards);
			nP = new NavPage(eHHcards, this);
			HomePage hP = new HomePage(eHHcards, this, nP);
			add(hP, "Home");
			HighScorePage hSP = new HighScorePage(eHHcards, this);
			add(hSP, "High Scores");
			hSP.setBackground(Color.RED);
			QuizPage qP = new QuizPage(eHHcards, this);
			add(qP, "Play");
			TemperaturePage tP = new TemperaturePage();
			SetAndInstructPage sP = new SetAndInstructPage(true, eHHcards, this, nP);
			add(sP, "Settings");
			SetAndInstructPage iP = new SetAndInstructPage(false, eHHcards, this, nP);
			add(iP, "Instructions");
			GamePage gP = new GamePage(eHHcards, this, qP, tP, nP);
			add(gP, "Game");
			gP.setBackground(Color.YELLOW);
		}
	}

	class NavPage extends JPanel implements ActionListener
	{
		private CardLayout nPcards;
		private EcoHausHolder nPeHH;
		public NavPage(CardLayout nPcardsIn, EcoHausHolder nPeHHIn)
		{
			setLayout(new GridLayout(3,1));
			nPcards = nPcardsIn;
			nPeHH = nPeHHIn;
			JPanel settingsPanel = new JPanel();
			JPanel instructionsPanel = new JPanel();
			JPanel exitPanel = new JPanel();
			JButton settings = new JButton("Settings");
			JButton instructions = new JButton("Instructions");
			JButton exit = new JButton("Exit");
			settingsPanel.add(settings);
			instructionsPanel.add(instructions);
			exitPanel.add(exit);
			settings.addActionListener(this);
			instructions.addActionListener(this);
			exit.addActionListener(this);
			add(settingsPanel);
			add(instructionsPanel);
			add(exitPanel);
		}

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
		}

		public void actionPerformed(ActionEvent evt)
		{
			String navButtonName = evt.getActionCommand();
			if(navButtonName.equals("Exit"))
			System.exit(1);
			else
			nPcards.show(nPeHH, navButtonName);
		}
	}

	class HomePage extends JPanel implements ActionListener
	{
		private CardLayout hPcards;
		private EcoHausHolder hPeHH;
		public HomePage(CardLayout hPcardsIn, EcoHausHolder hPeHHIn, NavPage hPnP)
		{
			setLayout(null);
			setBackground(Color.GREEN);
			hPcards = hPcardsIn;
			hPeHH = hPeHHIn;

			Font homeFont = new Font("Serif", Font.ITALIC, 50);
			JLabel welcomeTo = new JLabel("Welcome to");
			JLabel ecoHaus = new JLabel("Ecohaus");
			add(hPnP);
			add(welcomeTo);
			add(ecoHaus);

			welcomeTo.setFont(homeFont);
			ecoHaus.setFont(homeFont);
			JButton highScores = new JButton("High Scores");
			JButton playButton = new JButton("Play");
			highScores.addActionListener(this);
			playButton.addActionListener(this);
			add(highScores);
			add(playButton);

			hPnP.setBounds(0, 500, 300, 150);
			welcomeTo.setBounds(300, 300, 400, 50);
			ecoHaus.setBounds(325, 350, 200, 50);
			playButton.setBounds(325, 400, 200, 50);
			highScores.setBounds(750, 550, 150, 125);
		}

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
		}

		public void actionPerformed(ActionEvent evt)
		{
			String homeButtonName = evt.getActionCommand();
			hPcards.show(hPeHH, homeButtonName);
		}
	}

	class HighScorePage extends JPanel implements ActionListener
	{
		private CardLayout hSPcards;
		private EcoHausHolder hSPeHH;
		public HighScorePage(CardLayout hSPcardsIn, EcoHausHolder hSPeHHIn)
		{
			setLayout(new BorderLayout());
			hSPcards = hSPcardsIn;
			hSPeHH = hSPeHHIn;
			JButton backToHome = new JButton("Home");
			backToHome.addActionListener(this);
			add(backToHome, BorderLayout.SOUTH);
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
		}
		public void actionPerformed(ActionEvent evt)
		{
			hSPcards.show(hSPeHH, "Home");
		}
	}

	class GamePage extends JPanel implements ActionListener, MouseListener, KeyListener
	{
		private CardLayout gPcards;
		private EcoHausHolder gPeHH;
		public GamePage(CardLayout gPcardsIn, EcoHausHolder gPeHHIn, QuizPage gPqP, TemperaturePage gPtP, NavPage gPnP)
		{
			addMouseListener(this);
			addKeyListener(this);
			gPcards = gPcardsIn;
			gPeHH = gPeHHIn;
			setLayout(new BorderLayout());
			JPanel upperSection = new JPanel();
			upperSection.setLayout(new BorderLayout());
			upperSection.add(gPtP, BorderLayout.EAST);
			add(upperSection, BorderLayout.NORTH);
			JPanel lowerSection = new JPanel();
			lowerSection.setLayout(new BorderLayout());
			JPanel gameInfo = new JPanel();
			gameInfo.setLayout(new BorderLayout());
			gameInfo.add(gPnP, BorderLayout.SOUTH);
			lowerSection.add(gameInfo, BorderLayout.WEST);
			add(lowerSection, BorderLayout.CENTER);
		}
		public void actionPerformed(ActionEvent evt){}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
		}
		public void mousePressed( MouseEvent evt )
		{
			   requestFocusInWindow();
		}
		public void mouseReleased( MouseEvent evt ){}
		public void mouseClicked( MouseEvent evt ){}
		public void mouseEntered( MouseEvent evt ) {}
		public void mouseExited( MouseEvent evt ){}
		public void keyPressed( KeyEvent evt){}
		public void keyReleased( KeyEvent evt){}
		public void keyTyped( KeyEvent evt)
		{
			char exitKey = evt.getKeyChar();
			if(exitKey == 'x')
			gPcards.show(gPeHH, "Home");
		}
		   
	}

	class QuizPage extends JPanel implements ActionListener
	{
		private boolean questionAnswered;
		private CardLayout qPcards;
		private EcoHausHolder qPeHH;
		public QuizPage(CardLayout qPcardsIn, EcoHausHolder qPeHHIn)
		{
			qPcards = qPcardsIn;
			qPeHH = qPeHHIn;
			questionAnswered = false;
			setBackground(Color.DARK_GRAY);
			JButton next = new JButton("NEXT");
			next.addActionListener(this);
			add(next);
		}
		public void paintComponent(Graphics g)
		{
		     super.paintComponent(g);
		}
		public void actionPerformed(ActionEvent evt)
		{
	        qPcards.show(qPeHH, "Game");
		}
	}

	class TemperaturePage extends JPanel implements ActionListener
	{
		public TemperaturePage()
		{
			setBackground(Color.MAGENTA);
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			JButton buttonForNow = new JButton("Temperature Panel");
			add(buttonForNow);
		}
		public void actionPerformed(ActionEvent evt)
		{
		}
	}

	class SetAndInstructPage extends JPanel implements ActionListener, ChangeListener
	{
		private CardLayout sAIcards;
		private EcoHausHolder sAIeHH;
		public SetAndInstructPage(boolean which, CardLayout sAIcardsIn, EcoHausHolder sAIeHHIn, NavPage sAInP)
		{
			sAIcards = sAIcardsIn;
			sAIeHH = sAIeHHIn;
			setLayout(new BorderLayout());
			JPanel setInstBar = new JPanel();
			setInstBar.setLayout(new BorderLayout());
			JButton sAIHome = new JButton("Home");
			JButton sAIPlay = new JButton("Play");
			sAIHome.addActionListener(this);
			sAIPlay.addActionListener(this);
			JPanel homePlayBarButton = new JPanel();
			homePlayBarButton.setLayout(new GridLayout(2,1));
			homePlayBarButton.add(sAIHome);
			homePlayBarButton.add(sAIPlay);
			setInstBar.add(sAInP, BorderLayout.SOUTH);
			setInstBar.add(homePlayBarButton, BorderLayout.CENTER);
			JLabel setOrInstruct;
			setOrInstruct.setBackground(Color.YELLOW);
			if(which)
				setOrInstruct = new JLabel("Settings");
			else
				setOrInstruct = new JLabel("Instructions");
			setInstBar.add(setOrInstruct, BorderLayout.NORTH);
			add(setInstBar, BorderLayout.WEST);
			JButton setIntAreHere = new JButton("Settings/Instructions");
			add(setIntAreHere, BorderLayout.CENTER);
		}
		public void actionPerformed(ActionEvent evt)
		{
			String sAIName = evt.getActionCommand();
			sAIcards.show(sAIeHH, sAIName);
		}

		public void stateChanged(ChangeEvent evt)
		{
		}
	}

