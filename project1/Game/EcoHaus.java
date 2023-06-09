// James Cui
// Date: 4/24/2023
// EcoHaus.java
// This is the game project.
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Insets;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.io.PrintWriter;
import java.io.FileWriter;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.BorderFactory;


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

//The class that creates/holds all of the cards and makes the cardLayout
class EcoHausHolder extends JPanel
{
	public EcoHausHolder()
	{
		CardLayout eHHcards = new CardLayout();
		setLayout(eHHcards);
		NavPage nP = new NavPage(eHHcards, this);
		GetData gD = new GetData();
		gD.setCards(eHHcards);
		gD.seteHH(this);
		gD.setNP(nP);
		HomePage hP = new HomePage(gD);
		add(hP, "Home");
		HighScorePage hSP = new HighScorePage(gD);
		add(hSP, "High Scores");
		hSP.setBackground(Color.RED);
		QuizPage qP = new QuizPage(gD);
		add(qP, "Play");
		TemperaturePage tP = new TemperaturePage(gD);
		SetAndInstructPage sP = new SetAndInstructPage(true, gD);
		add(sP, "Settings");
		SetAndInstructPage iP = new SetAndInstructPage(false, gD);
		add(iP, "Instructions");
		GamePage gP = new GamePage(gD, qP);
		add(gP, "Game");
		gP.setBackground(Color.YELLOW);
	}
}

//The data class that, well, stores data.
class GetData
{
    private boolean tempChanging;
    private boolean response;
    private int orgsLeft;
	private int originalYears;
	private CardLayout dataCards;//Holds the cards.
	private NavPage dataNp;//Holds a Navpage for Game, will change later.
	private EcoHausHolder dataEcoHausHolder;//Class that holds the cards.
	private boolean dataGamePlaying;//Checks if game is playing.
	private boolean addCreature;//If the user is adding a creature.
	private String whichCreature;//Has the creature's name to show which.
	private boolean infoCreature;//If the user is looking for information on creature.
    private String temp;//Shows what level temperaature is.
    private String humid;
    private String sunlight;
    private int orgToAdd;//How many organisms can you add before year skips?
    private int yearsToPlay;//How many years to play until game ends?
    private int energy;//How much energy does the player have?
    private String name;//What's the player's name?

	public GetData()
	{
        tempChanging = false;
		response = false;
        infoCreature = false;
		dataGamePlaying = false;
        orgToAdd = 3;
        orgsLeft = 3;
        originalYears = 7;
        yearsToPlay = 7;
        name = "Player";
        temp = "t2";
        humid = "h2";
        sunlight = "s2";
	}

	public void setCards(CardLayout dataCardsIn)
	{
		dataCards = dataCardsIn;
	}

	public CardLayout getCards()
	{
		return dataCards;
	}

	public void setNP(NavPage dataNpin)
	{
		dataNp = dataNpin;
	}

	public NavPage getNp()
	{
		return dataNp;
	}
	
	public void seteHH(EcoHausHolder dataEcoHausHolderIn)
	{
		dataEcoHausHolder = dataEcoHausHolderIn;
	}
	
	public EcoHausHolder geteHH()
	{
		return dataEcoHausHolder;
	}

	public void setGamePlaying(boolean dataGamePlayingIn)
	{
		dataGamePlaying = dataGamePlayingIn;
	}

	public boolean getGamePlaying()
	{
		return dataGamePlaying;
	}

	public void setAddCreature(boolean addCreatureIn)
	{
		addCreature = addCreatureIn;
	}

	public boolean getAddCreature()
	{
		return addCreature;
	}

	public void setWhichCreature(String whichCreatureIn)
	{
		whichCreature = whichCreatureIn;
	}

	public String getWhichCreature()
	{
		return whichCreature;
	}

	public void setInfoCreature(boolean infoCreatureIn)
	{
		infoCreature = infoCreatureIn;
	}

	public boolean getInfoCreature()
	{
		return infoCreature;
	}

    public void setTemp(String tempIn)
    {
        temp = tempIn;
    }

    public String getTemp()
    {
        return temp;
    }
    public void setHumidity(String humidIn)
    {
		humid = humidIn;
	}
    
    public String getHumidity()
    {
		return humid;
	}
    
    public void setSunlight(String sunlightIn)
    {
		sunlight = sunlightIn;
	}
    
    public String getSunlight()
    {
		return sunlight;
	}
    
    public void setOrgToAdd(int orgToAddIn)
    {
        orgToAdd = orgToAddIn;
    }

    public int getOrgToAdd()
    {
        return orgToAdd;
    }

    public void setYearsToPlay(int yearsToPlayIn)
    {
        yearsToPlay = yearsToPlayIn;
        originalYears = yearsToPlayIn;
    }

    public void subYears()
    {
		--yearsToPlay;
	}

    public int getOriginalYears()
    {
		return originalYears;
	}
    
    public int getYearsToPlay()
    {
        return yearsToPlay;
    }

    public void setOrgsLeft()
    {
		orgsLeft = orgToAdd;
	}
	
	public void subOrgs()
	{
		--orgsLeft;
	}

    public int getOrgsLeft()
	{
		return orgsLeft;
	}
	
    public void addEnergy(int energyIn)
    {
        energy += energyIn;
    }

    public int getEnergy()
    {
        return energy;
    }

    public void setName(String nameIn)
    {
        name = nameIn;
    }

    public String catchName()
    {
        return name;
    }
    
    public void setResponse(boolean responseIn)
    {
		response = responseIn;
	}
	
	public boolean getResponse()
	{
		return response;
	}

    public void setTempChanging(boolean tempChangingIn)
    {
        tempChanging = tempChangingIn;
    }

    public boolean getTempChanging()
    {
        return tempChanging;
    }
    
    public Scanner makeScanner(String scanName)
	{
        Scanner read = new Scanner(System.in);
		File readFile = new File(scanName);
		try
		{
			read = new Scanner(readFile);
		}
		catch(FileNotFoundException e)
		{
			System.err.println("\n\n " + scanName + "was not found");
			System.exit(6);
		}
        return read;
	}
}

//A class that holds three buttons which is seen repeatedly in other 
// panels. This class is a bit bugged, so I am using a clone of this 
//code for other classes.
class NavPage extends JPanel implements ActionListener
{
	private CardLayout nPcards;//Passed in cards. (switch cards)
	private EcoHausHolder nPeHH;//Passed in EcoHausHolder. (switch cards)
	private JButton settings;//Settings button
	private JButton instructions;//Instructions button
	private JButton exit;//Exit button

	public NavPage(CardLayout nPcardsIn, EcoHausHolder nPeHHIn)
	{
		setLayout(new GridLayout(3,1));
		nPcards = nPcardsIn;
		nPeHH = nPeHHIn;
        
		JPanel settingsPanel = new JPanel();
        settingsPanel.setBackground(Color.GREEN);
		JPanel instructionsPanel = new JPanel();
        instructionsPanel.setBackground(Color.GREEN);
		JPanel exitPanel = new JPanel();
        exitPanel.setBackground(Color.GREEN);
        
		settings = new JButton("Settings");
		instructions = new JButton("Instructions");
		exit = new JButton("Exit");
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

    //Disables all the buttons, not used yet.
	public void disableButtons()
	{
		settings.setEnabled(true);
		instructions.setEnabled(true);
		exit.setEnabled(true);
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

//HomePage is the start page, shows the title, a play button, and a 
//button to high scores.
class HomePage extends JPanel implements ActionListener
{
	private CardLayout hPcards;
	private EcoHausHolder hPeHH;
	private GetData hPgD;
	public HomePage(GetData hPgDin)
	{
		setLayout(null);
		setBackground(Color.GREEN);
		hPgD = hPgDin;
		hPcards = hPgD.getCards();
		hPeHH = hPgD.geteHH();

		Font homeFont = new Font("Serif", Font.ITALIC, 50);
		JLabel welcomeTo = new JLabel("Welcome to");
		JLabel ecoHaus = new JLabel("Ecohaus");
		NavPage hPnP = new NavPage(hPcards, hPeHH);
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

		hPnP.setBounds(0, 500, 150, 150);
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
		if(homeButtonName.equals("Play") && hPgD.getResponse())
			hPcards.show(hPeHH, "Game");
		else
			hPcards.show(hPeHH, homeButtonName);
	}
}

//Shows the high scores gotten by previous players.
class HighScorePage extends JPanel implements ActionListener
{
    private GetData hSPgD;
	private CardLayout hSPcards;//Passed in cards. (switch cards)
	private EcoHausHolder hSPeHH;//Passed in EcoHausHolder. (switch cards)
	private Scanner highScoreRead;//Reads EcoHighScores
	public HighScorePage(GetData hSPgDin)
	{
		setLayout(new BorderLayout());
        hSPgD = hSPgDin;
		hSPcards = hSPgD.getCards();
		hSPeHH = hSPgD.geteHH();
		JButton backToHome = new JButton("Home");
		backToHome.addActionListener(this);
		add(backToHome, BorderLayout.SOUTH);
        highScoreRead = hSPgD.makeScanner("ecoHighScores.txt");
	}

	public void paintComponent(Graphics g)
	{
        Font highScoreFont = new Font("Serif", Font.ITALIC, 20);
		String hSNextLine;
        int incScore = 0;
		super.paintComponent(g);
        setFont(highScoreFont);
		g.drawString("High Scores", 150, 50);
        while(highScoreRead.hasNext())
        {
            hSNextLine = highScoreRead.nextLine();
            g.setColor(Color.BLACK);
            g.drawString(hSNextLine, 150, 100 + (30 * incScore));
            ++incScore;
        }
        incScore = 0;
        highScoreRead = hSPgD.makeScanner("ecoHighScores.txt");
	}

	public void actionPerformed(ActionEvent evt)
	{
		hSPcards.show(hSPeHH, "Home");
	}
}

//Plays the game, uses keyboard to return back home.
class GamePage extends JPanel implements ActionListener, MouseListener, KeyListener
{
    private JTextField humidField, sunField, tempField;
    private String yearStr, orgsStr, energyStr;
    private PrintWriter writeScore;//Write into ecoHighScores.txt
    private JTextField nameDisplay;//Receives a name.
    private JTextField energyDisplay;//Displays Player's energy.
    private JTextField organismDisplay;//Displays organisms per year.
    private JTextField yearDisplay;//Displays years.
    private JTextField natDisaster;//The TextField to write natural disasters in.
	private CardLayout gPcards;//Passed in cards. (switch cards)
	private EcoHausHolder gPeHH;//Passed in EcoHausHolder. (switch cards)
	private GetData gPgD;//Passed in data(all sorts of functions)
	public GamePage(GetData gPgDin, QuizPage gPqP)
	{
        makePrintWriter();
		addMouseListener(this);
		addKeyListener(this);
        setLayout(new BorderLayout());
        
		boolean gPgamePlaying = true;
        natDisaster = new JTextField();
        tempField = new JTextField();
        humidField = new JTextField();
        sunField = new JTextField();
		gPgD = gPgDin;
		gPgD.setGamePlaying(gPgamePlaying);
		gPcards = gPgD.getCards();
		gPeHH = gPgD.geteHH();
        
		NavPage gPnP = gPgD.getNp();
        TemperaturePage gPtP = new TemperaturePage(gPgD);
		GameMainFrame gMF = new GameMainFrame(gPgD);
		Organism organismOne = new Organism("wildGrass");
		Organism organismTwo = new Organism("antelope");
		Organism organismThree = new Organism("muskrat");
		Organism organismFour = new Organism("wolverine");
        
		JPanel upperSection = new JPanel();
		upperSection.setLayout(new BorderLayout());
		upperSection.add(gPtP, BorderLayout.EAST);
        JLabel pressX = new JLabel("Press the screen and x to go home.");
        JPanel pressXholder = new JPanel();
        pressXholder.add(pressX);
        JPanel condHolder = new JPanel();
        condHolder.setLayout(new GridLayout(3,1));
        condHolder.add(tempField);
        condHolder.add(humidField);
        condHolder.add(sunField);
        upperSection.add(pressXholder, BorderLayout.SOUTH);
        upperSection.add(condHolder, BorderLayout.CENTER);
		add(upperSection, BorderLayout.NORTH);
        
		JPanel mainGamePanel = new JPanel();
		mainGamePanel.setLayout(new BorderLayout());
        
		JPanel lowerSection = new JPanel();
		lowerSection.setLayout(new BorderLayout());
        
		JPanel gameInfo = new JPanel();
		gameInfo.setLayout(new BorderLayout());
		gameInfo.add(gPnP, BorderLayout.SOUTH);
        
        JPanel gameInfoPart = new JPanel();
        gameInfoPart.setBackground(Color.YELLOW);
        gameInfoPart.setLayout(new GridLayout(8,1));
        
        JLabel energyLabel = new JLabel("# of Energy");
        energyDisplay = new JTextField();
        energyStr = " " + gPgD.getEnergy();
        energyDisplay.setText(energyStr);
        JLabel organismLabel = new JLabel("# of turns");
        organismDisplay = new JTextField();
        JLabel yearLabel = new JLabel("# of Years");
        yearDisplay = new JTextField(gPgD.getYearsToPlay());
        JLabel nameLabel = new JLabel("Enter your name");
        nameDisplay = new JTextField("...");
        nameDisplay.setEditable(true);
        nameDisplay.addActionListener(this);
        
        gameInfoPart.add(nameLabel);
        gameInfoPart.add(nameDisplay);
        gameInfoPart.add(energyLabel);
        gameInfoPart.add(energyDisplay);
        gameInfoPart.add(organismLabel);
        gameInfoPart.add(organismDisplay);
        gameInfoPart.add(yearLabel);
        gameInfoPart.add(yearDisplay);
        gameInfo.add(gameInfoPart, BorderLayout.CENTER);
		lowerSection.add(gameInfo, BorderLayout.WEST);
		lowerSection.add(mainGamePanel, BorderLayout.CENTER);

        JPanel addOrgHolder = new JPanel();
        addOrgHolder.setLayout(new BorderLayout());
		JPanel manageOrg = new JPanel();
		manageOrg.setLayout(new GridLayout(1,4));
		manageOrg.add(organismOne);
		manageOrg.add(organismTwo);
		manageOrg.add(organismThree);
		manageOrg.add(organismFour);

		addOrgHolder.add(manageOrg, BorderLayout.CENTER);
        addOrgHolder.add(natDisaster, BorderLayout.NORTH);
		mainGamePanel.add(gMF, BorderLayout.CENTER);
		mainGamePanel.add(addOrgHolder, BorderLayout.SOUTH);
		add(lowerSection, BorderLayout.CENTER);
	}

    //Makes the only PrintWrter
    public void makePrintWriter()
    {
        String highScore = new String("ecoHighScores.txt");
        File scoreFile = new File(highScore);
        try
        {
            writeScore = new PrintWriter( new FileWriter(scoreFile, true));
            System.out.println("WE ARE PRINTING");
        }
        catch(IOException i)
        {
            System.out.println("\n\n IOException \n\n");
            System.exit(1);
        }
    }

	public void actionPerformed(ActionEvent evt)
	{
        String newName = evt.getActionCommand();
        gPgD.setName(newName);
	}

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

	//Main Frame of the game, draws out the images of the organisms and makes
	//them move. It places down a new creature based on the button pressed
	//and where the mouse was clicked after the button was pressed.
	class GameMainFrame extends JPanel implements ActionListener, MouseListener, MouseMotionListener
	{
        private String gTemp, gHumid, gSun;
        private int randSuggest;
        private int timePass;
		private int mouseX;//The mouse's x location
		private int mouseY;//The mouse's y location
		private int numOfOrgs;//Number of organisms
		private int[] speciesNum;
		private int[] creatureX;//Creature's x location
		private int[] creatureY;//Creature's y location
		private int[] chooseCreature;//Chooses which creature to draw.
		private Timer creatureTimer;//Timer for animation.
		private Image[] allOrgs;//Image containing each organism.
		private String[] orgNames;//Names of each organism
		private GetData gMFgD;//The GetData object.
		public GameMainFrame(GetData gMFgDin)
		{
			addMouseListener(this);
			addMouseMotionListener(this);
			randSuggest = (int)(Math.random()*4+1);
			gMFgD = gMFgDin;
            speciesNum = new int[]{0,0,0,0};
			allOrgs = new Image[5];
			orgNames = new String[]{"wildGrass.jpg","antelope.jpg","muskrat.jpg","wolverine.jpg","background.jpg"};
			numOfOrgs = 0;
			creatureX = new int[20];
			creatureY = new int[20];
			chooseCreature = new int[20];
			setBackground(Color.CYAN);
			creatureTimer = new Timer(5000, this);
			creatureTimer.start();
			drawAllImg();
		}
		
		//Takes ths strings in orgNames and turns them into images.
		public void drawAllImg()
		{
			File orgFile;
			for(int o = 0; o < allOrgs.length; ++o)
			{
				orgFile = new File(orgNames[o]);
			try
			{
				allOrgs[o] = ImageIO.read(orgFile);
			}
			catch(IOException e)
			{
				System.err.println("\n\n Cannot find "+orgNames[o]+
				"\n\n");
				System.exit(4);
			}
			}
		}

		//paints the background as well as changing the creature's location
		//and drawing their image on that spot instead.
		public void paintComponent(Graphics g)
		{
            String humidString = " Humidity: " + gHumid;
            humidField.setText(humidString);
            String sunString = " Sunlight: " + gSun;
            sunField.setText(sunString);
            String tempString = " Temperature: " + gTemp;
            tempField.setText(tempString);
            orgsStr = " " + gPgD.getOrgsLeft();
			organismDisplay.setText(orgsStr);
			yearStr = " " + gPgD.getYearsToPlay();;
			yearDisplay.setText(yearStr);
            energyStr = " " + gPgD.getEnergy();
            energyDisplay.setText(energyStr);
			super.paintComponent(g);
            gTemp = gPgD.getTemp();
            gHumid = gPgD.getHumidity();
            gSun = gPgD.getSunlight();
            Font instructFont = new Font("Dialog", Font.ITALIC, 20);
            setFont(instructFont);
            String which = gPgD.getWhichCreature();
            if(gPgD.getInfoCreature())
            {
				setBackground(Color.BLUE);
                if(which.equals(orgNames[0]))
                {
                    g.drawImage(allOrgs[0], 600, 0, 100, 100, this);
                    g.drawString("Wild Grass", 100, 100);
                    g.drawString("Kingdom: Plantae", 100, 140);
                    g.drawString("Familly: Poaceae, or Gramineae", 100, 180);
                    g.drawString("Grasslands are estimated to cover "+
                            "around 40.5% of the earth", 100, 220);
                    g.drawString("Producer in the food chain", 100, 260);
                    g.drawString("Eaten by Herbivores", 100, 300);
                    g.drawString("Preferred humidity: high", 100, 340);
                    g.drawString("Preferred sunlight: high", 100, 380);
                    g.drawString("Preferred temperature: high", 100, 420);
                }
                else if(which.equals(orgNames[1]))
                {
                    g.drawImage(allOrgs[1], 600, 0, 100, 100, this);
                    g.drawString("Antelope", 100, 100);
                    g.drawString("Kingdom: Animalia", 100, 140);
                    g.drawString("Family: Bovidae", 100, 180);
                    g.drawString("A wastebasket taxon comprising of any"
                       + " old world grazing hoofed animals", 100, 220);
                    g.drawString("belonging to the order Atriodactyla "+
                    "In the family Bovidae", 100, 260);
                    g.drawString("Herbivore in the food chain", 100, 300);
                    g.drawString("Eaten by Carnivores", 100, 340);
                    g.drawString("Preferred humidity: low", 100, 380);
                    g.drawString("Preferred sunlight: high", 100, 420);
                    g.drawString("Preferred temperature: high", 100, 460);
                }
                else if(which.equals(orgNames[2]))
                {
                    g.drawImage(allOrgs[2], 600, 0, 100, 100, this);
                    g.drawString("Muskrat", 100, 100);
                    g.drawString("Kingdom: Animalia", 100, 100);
                    g.drawString("Familly: Cricetidae", 100, 140);
                    g.drawString("Muskrats are referred to as rats "+
                        "as they are medium sized rodents.", 100, 180);
                    g.drawString("They are not related to rats or the"+
                        " similar beavers, though.", 100, 220);
                    g.drawString("Herbivore in the food chain", 100, 260);
                    g.drawString("Eaten by Carnivores", 100, 300);
                    g.drawString("Preferred humidity: high", 100, 340);
                    g.drawString("Preferred sunlight: medium", 100, 380);
                    g.drawString("Preferred temperature: medium", 100, 420);
                }
                else if(which.equals(orgNames[3]))
                {
                    g.drawImage(allOrgs[3], 600, 0, 100, 100, this);
                    g.drawString("Wolverine", 100, 100);
                    g.drawString("Kingdom: Animalia", 100, 140);
                    g.drawString("Familly: Mustelidae", 100, 180);
                    g.drawString("The largest land-dwelling species of"+
                        " the family Mustelidae, "
                        , 100, 220);
                    g.drawString(", this species is mainly a scavenger,"
                        + "but it can kill prey ", 100, 260);
                    g.drawString("like deer that are many times it's size.", 100, 300);
                    g.drawString("Carnivore in the food chain", 100, 340);
                    g.drawString("Not eaten by anything", 100, 380);
                    g.drawString("Preferred humidity: medium", 100, 420);
                    g.drawString("Preferred sunlight: low", 100, 460);
                    g.drawString("Preferred temperature: low", 100, 500);
                }
            }
            else
            {
				if(gPgD.getYearsToPlay() > 0)
				{
						g.drawImage(allOrgs[4], 0, 0, 830, 600, this);
						int xTop, xLow, yTop, yLow, moveCreature;
						xTop = this.getWidth() - 50;
						xLow = yLow = 50;
						yTop = this.getHeight() - 50;
						if(timePass == 0)
						{
							if(gHumid.equals("h1"))
							{
								g.setColor(Color.ORANGE);
								g.fillRect(0, 170, 800, 200);
							}
							else if(gHumid.equals("h2") || gHumid.equals("h3"))
							{
								g.setColor(Color.CYAN);
								for(int w = 0; w < 16; ++w)
								{
									g.drawLine(50*w, 0, 50*w, 200);
								}
								if(gHumid.equals("h3"))
								{
									g.setColor(Color.LIGHT_GRAY);
									g.fillRect(0, 170, 800, 30);
								}
							}
							if(gTemp.equals("t1"))
							{
								g.setColor(Color.WHITE);
								g.fillRect(0, 200, 800, 300);
							}
							else if(gTemp.equals("t3"))
							{
								g.setColor(Color.YELLOW);
								g.fillRect(0, 170, 800, 20);
							}
							g.setColor(Color.YELLOW);
							if(gSun.substring(1,2).equals("1"))
								g.fillOval(700, 100, 50, 50);
							else if(gSun.substring(1,2).equals("2"))
								 g.fillOval(550, 70, 50, 50);
							else
								g.fillOval(400, 40, 50, 50);
							for(int x = 0; x < numOfOrgs; ++x)
							{
								if(chooseCreature[x] != 0)
								{
									moveCreature = (int)(Math.random()*3+0);
									if(moveCreature == 1)
									   creatureX[x]+=3;
									else if(moveCreature == 2)
										creatureX[x]-=3;
									moveCreature = (int)(Math.random()*3+0);
									if(moveCreature == 1)
										 creatureY[x]+=3;
									else if(moveCreature == 2)
										 creatureY[x]-=3;
									if(creatureX[x] >= xTop)
										creatureX[x] = xTop;
									if(creatureX[x] <= xLow)
										creatureX[x] = xLow;
									if(creatureY[x] >= yTop)
										creatureY[x] = yTop;
									if(creatureY[x] <= yLow)
										creatureY[x] = yLow;
								}
							}
							for(int y = 0; y < numOfOrgs; ++y)
							{
								if(chooseCreature[y] != 10000)
									g.drawImage(allOrgs[chooseCreature[y]], creatureX[y], creatureY[y],50, 50, this);
							}
						}
						else
						{
							Font yearFont = new Font("Dialog", Font.BOLD, 40);
							setFont(yearFont);
							g.fillRect(0,0,900,900);
							g.setColor(Color.YELLOW);
							g.drawString("Year " + (gPgD.getOriginalYears() - gPgD.getYearsToPlay()),100, 100);
							--timePass;
						}
					}
					else
					{
						g.fillRect(0,0,900,900);
						g.setColor(Color.YELLOW);
						g.drawString("You finished the game with " + gPgD.getEnergy(), 100, 100);
						if(gPgD.getEnergy() < 100)
						{
							if(randSuggest == 1)
							{
								g.drawString("To do better, put more grass "+
											"than herbivores!", 100, 140);
							}
							else if(randSuggest == 2)
							{
								g.drawString("To do better, put more "+
											"herbivores than carnivores!", 100, 140);
							}
							else if(randSuggest == 3)
							{
								g.drawString("To do better, change conditions "
												+"to suit the creature you place!", 100, 140);
							}
						}
					}
            }
        }

        public void goodWeather()
        {
            int randX, randY;
            if(numOfOrgs == (creatureX.length - 4))
            {
                creatureX = getArray(creatureX);
                creatureY = getArray(creatureY);
                chooseCreature = getArray(chooseCreature);
            }
            if(gSun.equals("s3") && gHumid.equals("h3") && gTemp.equals("t3"))
            {
                ++numOfOrgs;
                chooseCreature[numOfOrgs] = 0;
                ++speciesNum[0];
                randX = (int)(Math.random()*(this.getWidth() - 50)+50);
                creatureX[numOfOrgs] = randX;
                randY = (int)(Math.random()*(this.getHeight() - 50)+50);
                creatureY[numOfOrgs] = randY;
                System.out.println(creatureX[numOfOrgs] + " " + creatureY[numOfOrgs]);
            }
            if(gSun.equals("s3") && gHumid.equals("h1") && gTemp.equals("t3"))
            {
                ++numOfOrgs;
                chooseCreature[numOfOrgs] = 1;
                ++speciesNum[1];
                randX = (int)(Math.random()*(this.getWidth() - 50)+50);
                creatureX[numOfOrgs] = randX;
                randY = (int)(Math.random()*(this.getHeight() - 50)+50);
                creatureY[numOfOrgs] = randY;
            }
            if(gSun.equals("s2") && gHumid.equals("h3") && gTemp.equals("t2"))
            {
                ++numOfOrgs;
                chooseCreature[numOfOrgs] = 2;
                ++speciesNum[2];
                randX = (int)(Math.random()*(this.getWidth() - 50)+50);
                creatureX[numOfOrgs] = randX;
                randY = (int)(Math.random()*(this.getHeight() - 50)+50);
                creatureY[numOfOrgs] = randY;
            }
            if(gSun.equals("s1") && gHumid.equals("h2") && gTemp.equals("t1"))
            {
                ++numOfOrgs;
                chooseCreature[numOfOrgs] = 3;
                ++speciesNum[3];
                randX = (int)(Math.random()*(this.getWidth() - 50)+50);
                creatureX[numOfOrgs] = randX;
                randY = (int)(Math.random()*(this.getHeight() - 50)+50);
                creatureY[numOfOrgs] = randY;
            }
        }

        public void creatureEaten()
        {
			int speciesEat = 0;
			int eatUp = 0;
			int eatIndex = 0;
			int numDead = 0;
            int eatingOrganism = 0;
			int eatOrganism = 0;
			int deathIndex = 0;
			int countUp = 0;
			int numToEat = 0;
			boolean whatToEat = true;
            for(int c = 0; c < creatureX.length; ++c)
                System.out.println(chooseCreature[c]);
			for(int e = 0; e < numOfOrgs+1; ++e)
			{
                System.out.println("Switch creature");
				numToEat = 1;
/*				while(chooseCreature[eatIndex] == speciesEat)
                     ++eatIndex;
                ++eatUp;
                eatingOrganism = chooseCreature[eatIndex];
                System.out.println(eatingOrganism);
                if(eatUp == speciesNum[speciesEat])
                {
                    eatUp = 0;
                    eatIndex = 0;
                    ++speciesEat;
                }*/
                eatingOrganism = chooseCreature[e];
				if(eatingOrganism == 1)
					numToEat = 2;
				if(eatingOrganism == 3)
					whatToEat = false;
                if(chooseCreature[eatingOrganism] != 0 && chooseCreature[eatingOrganism] != 10000)
                {
                    if(whatToEat && speciesNum[0] > 0 || !whatToEat && (speciesNum[1] > 0 || speciesNum[2] > 0))
                    {
                        for(int t = 0; t < numToEat; ++t)
                        {
                            System.out.println("Eating creature");
                            if(whatToEat)
                                eatOrganism = (int)(Math.random()*speciesNum[0]+1);
                            else
                                eatOrganism = (int)(Math.random()*(speciesNum[1]+speciesNum[2])+1);
                            while(!(countUp == eatOrganism))
                            {
                                if(countUp == (creatureX.length - 1))
                                {
                                    creatureX = getArray(creatureX);
                                    creatureY = getArray(creatureY);
                                    chooseCreature = getArray(chooseCreature);
                                }
                                 System.out.println(eatingOrganism + " Find creature " + 
                                countUp + " " + eatOrganism + " " +
                                deathIndex + " " + chooseCreature[deathIndex]);
                                if(whatToEat)
                                {
                                    if(chooseCreature[deathIndex] == 0)
                                        ++countUp;
                                    ++deathIndex;
                                }
                                else
                                {
                                    if(chooseCreature[deathIndex] == 1 || chooseCreature[deathIndex] == 2)
                                        ++countUp;
                                    ++deathIndex;
                                }
                            }
                            --deathIndex;
                            whatToEat = true;
                            System.out.println(eatingOrganism + " ate " + chooseCreature[deathIndex]);
                            countUp = 0;
                            if(chooseCreature[deathIndex] == 0)
                                --speciesNum[0];
                            else if(chooseCreature[deathIndex] == 1)
                                --speciesNum[1];
                            else if(chooseCreature[deathIndex] == 2)
                                --speciesNum[2];
                            else if(chooseCreature[deathIndex] == 3)
                                --speciesNum[3];
                            chooseCreature[deathIndex] = 10000;
                            creatureX[deathIndex] = 10000;
                            creatureY[deathIndex] = 10000;
                            ++numDead;
                            deathIndex = 0;
                        }
                    }
                    else
                    {
                        System.out.println("Starved to death");
                         chooseCreature[e] = 10000;
                         creatureX[e] = 10000;
                         creatureY[e] = 10000;
                         if(eatingOrganism == 0)
                            --speciesNum[0];
                         else if(eatingOrganism == 1)
                            --speciesNum[1];
                         else if(eatingOrganism == 2)
                            --speciesNum[2];
                         else if(eatingOrganism == 3)
                            --speciesNum[3];
                         ++numDead;
                        for(int x = 0; x < speciesNum.length; ++x)
                        {
                            if(speciesNum[x] < 0)
                                speciesNum[x] = 0;
                        }
                    }
                }
            }
            numOfOrgs -= numDead;
			chooseCreature = deathArray(chooseCreature);
			creatureX = deathArray(creatureX);
            creatureY = deathArray(creatureY);
					
        }

        public void amountOfEnergy()
        {
			int addOrganism = 0;
			gPgD.addEnergy(speciesNum[0]);
			gPgD.addEnergy(speciesNum[1] * 5);
			gPgD.addEnergy(speciesNum[2] * 2);
			gPgD.addEnergy(speciesNum[3] * 7);
            energyStr = " " + gPgD.getEnergy();
            energyDisplay.setText(energyStr);
        }

        public void naturalDisaster()
        {
            int disasterChance = (int)(Math.random()*100+1);
            if(disasterChance <= 5)
            {
                natDisaster.setText("A harsh drought hit the land!");
                gHumid = "h1";
                gSun = "s3";
                gTemp = "t3";
            }
            else if(disasterChance > 5 && disasterChance <= 10)
            {
                natDisaster.setText("A fierce snowstorm hit the land!");
                gHumid = "h2";
                gSun = "s1";
                gTemp = "t1";
            }
            else if(disasterChance > 10 && disasterChance <= 20)
            {
                natDisaster.setText("Heavier rains start to appear.");
                gHumid = "h3";
                gSun = "s1";
                gTemp = "t1";
            }
            repaint();
        }


		public int[] deathArray(int[] dyingArray)
		{
            int lifeIndex = 0;
			int[] lifeArray = new int[dyingArray.length];
			for(int z = 0; z < dyingArray.length; ++z)
			{
				if(!(dyingArray[z] == 10000))
                {
					lifeArray[lifeIndex] = dyingArray[z];
                    ++lifeIndex;
                }
			}
			return lifeArray;
		}

		//Takes an array that was passed in and expands it.
		public int[] getArray(int[] expandArray)
		{
			int[] bigArray = new int[expandArray.length + 30];
			for(int a = 0; a < expandArray.length; ++a)
				bigArray[a] = expandArray[a];
			return bigArray;
		}

		//Repaints and gets focus for the timer.
		public void actionPerformed(ActionEvent evt)
		{
			repaint();
		}

		//If the mouse is pressed and you have previously pressed a +
		//button, there will be a new organism there, this makes the
		//organism's data, will also check organisms per turn/year.
		public void mousePressed( MouseEvent evt )
		{
			if(gMFgD.getAddCreature())
			{
				requestFocusInWindow();
				if(numOfOrgs == creatureX.length)
				{
					creatureX = getArray(creatureX);
					creatureY = getArray(creatureY);
					chooseCreature = getArray(chooseCreature);
				}
				creatureX[numOfOrgs] = mouseX;
				creatureY[numOfOrgs] = mouseY;
				if(gMFgD.getWhichCreature().equals("wildGrass.jpg"))
				{
					chooseCreature[numOfOrgs] = 0;
					++speciesNum[0];
				}
				else if(gMFgD.getWhichCreature().equals("antelope.jpg"))
				{
					chooseCreature[numOfOrgs] = 1;
					++speciesNum[1];
				}
				else if(gMFgD.getWhichCreature().equals("muskrat.jpg"))
				{
					chooseCreature[numOfOrgs] = 2;
					++speciesNum[2];
				}
				else if(gMFgD.getWhichCreature().equals("wolverine.jpg"))
				{
					chooseCreature[numOfOrgs] = 3;
					++speciesNum[3];
				}
				gPgD.subOrgs();
                if(gPgD.getOrgsLeft() <= 0)
                {
                    gPgD.subYears();
                    if(gPgD.getYearsToPlay() <= 0)
                    {
                        if(gPgD.getEnergy() > 10)
                        {
                            writeScore.println(gPgD.catchName()+ " " +
                            gPgD.getYearsToPlay() + " " +
                            gPgD.getOrgToAdd() + " " + gPgD.getEnergy());
                            writeScore.close();
                        }
                    }
                    else
                    {
                        gPgD.setOrgsLeft();
                        gPgD.setResponse(false);
                        timePass = 200;
                        naturalDisaster();
                        goodWeather();
                        creatureEaten();
                        amountOfEnergy();
                        gPgD.setTempChanging(false);
                        gPcards.show(gPeHH, "Play");
                    }
                }
                yearStr = " " + gPgD.getYearsToPlay();
                yearDisplay.setText(yearStr);
                orgsStr = " " + gPgD.getOrgsLeft();
                organismDisplay.setText(orgsStr);
                ++numOfOrgs;
                repaint();
			}
		}
		public void mouseReleased( MouseEvent evt ){}
		public void mouseClicked( MouseEvent evt ){}
		public void mouseEntered( MouseEvent evt ) {}
		public void mouseExited( MouseEvent evt ){}
		//Tracks the mouse's location.
		public void mouseMoved(MouseEvent evt)
		{
			mouseX = evt.getX();
			mouseY = evt.getY();
		}
		public void mouseDragged(MouseEvent evt){}

	}


	//Each organism has this panel used for it. This program makes the
	//buttons + and i and responds to them.
	class Organism extends JPanel implements ActionListener
	{
		private String organismName;//The name of the organism of this panel.
		public Organism(String organismNameIn)
		{
			setLayout(new GridLayout(2,1));
			organismName = organismNameIn;
			JLabel organismNameLabel = new JLabel(organismName);
			add(organismNameLabel);
			JPanel instAndAdd = new JPanel();
			add(instAndAdd);
			JButton addCreature = new JButton("+");
			addCreature.addActionListener(this);
			instAndAdd.add(addCreature);
			JButton infoCreature = new JButton("info");
			infoCreature.addActionListener(this);
			instAndAdd.add(infoCreature);
		}

		public void actionPerformed(ActionEvent evt)
		{
            boolean setInfo = gPgD.getInfoCreature();
			String gPbuttonName = evt.getActionCommand();
			if(gPbuttonName.equals("+"))
				gPgD.setAddCreature(true);
			else if(gPbuttonName.equals("info"))
            {
                if(setInfo)
                        gPgD.setInfoCreature(false);
                else
                    gPgD.setInfoCreature(true);
            }
			gPgD.setWhichCreature(organismName + ".jpg");
		}
	}
	   
}

class QuizPage extends JPanel implements ActionListener
{
    private JTextField question;
    private JPanel downPanel;
    private JRadioButton[] answers;//The array with the button for each answer
	private JTextArea rightOrWrong;//TextArea which displays correct or incorrect.
	private Scanner scanQuiz;//Read ecoQuiz.txt
	private GetData qPgD;//Store questionAnswered.
	private String prevButton;//The button which was previously selected.
	private boolean nextPress;//Tracks and determines what to do when next is pressed.
	private boolean[] questionAnswered;//Is the question answered corrrectly?
	private CardLayout qPcards;//Passed in cards. (switch cards)
	private EcoHausHolder qPeHH;//Passed in EcoHausHolder. (switch cards)
    private JButton next;//The next button on the top.
    private ButtonGroup qPbG;//The buttonGroup for answers.

    
	public QuizPage(GetData qPgDin)
	{
        questionAnswered = new boolean[40];
        qPgD = qPgDin;
        scanQuiz = qPgD.makeScanner("ecoQuiz.txt");
        qPbG = new ButtonGroup();
        String quizLine = new String("");
        setLayout(new GridLayout(3,1));
        nextPress = false;
        qPcards = qPgD.getCards();
        qPeHH = qPgD.geteHH();
        setBackground(Color.DARK_GRAY);
        question = new JTextField();
        
        JPanel quizTop = new JPanel();
        quizTop.setLayout(new BorderLayout());
        next = new JButton("NEXT");
        next.setEnabled(false);
        next.addActionListener(this);
        quizTop.add(next, BorderLayout.NORTH);
        quizTop.add(question, BorderLayout.CENTER);
        
        rightOrWrong = new JTextArea(5,5);
        rightOrWrong.setLineWrap(true);
        rightOrWrong.setWrapStyleWord(true);
        rightOrWrong.setEditable(false);
        rightOrWrong.setMargin(new Insets(10,10,10,10));
        
        downPanel = new JPanel();
        downPanel.setLayout( new GridLayout(2,2));
        answers = new JRadioButton[4];
        int whichQuestion = (int)(Math.random()*12+1);
        questionAnswered[whichQuestion] = true;
        String resetLine;
        for(int r = 0; r < whichQuestion-1; ++r)
        {
            for(int s = 0; s < 7; ++s)
                scanQuiz.nextLine();
        }
        question.setText(scanQuiz.nextLine());
        for(int r = 0; r < 4; ++r)
        {
            resetLine = scanQuiz.nextLine();
            answers[r] = new JRadioButton(resetLine);
            answers[r].addActionListener(this);
            qPbG.add(answers[r]);
            downPanel.add(answers[r]);
        }
        add(quizTop);
        add(rightOrWrong);
        add(downPanel);
    }
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}

    public void resetQuiz()
    {
        qPbG.clearSelection();
        downPanel.setLayout(new GridLayout(2,2));
        int whichQuestion = (int)(Math.random()*12+1);
        while(questionAnswered[whichQuestion] == true)
                whichQuestion = (int)(Math.random()*12+1);
        questionAnswered[whichQuestion] = true;
        System.out.println(whichQuestion + " which");
        String resetLine = "";
        for(int r = 0; r < whichQuestion-1; ++r)
        {
            for(int s = 0; s < 7; ++s)
                scanQuiz.nextLine();
        }
        question.setText(scanQuiz.nextLine());
        for(int r = 0; r < 4; ++r)
        {
            resetLine = scanQuiz.nextLine();
            answers[r].setText(resetLine);
            answers[r].addActionListener(this);
            qPbG.add(answers[r]);
            downPanel.add(answers[r]);
        }
        
    }
    
	public void actionPerformed(ActionEvent evt)
	{
        String quizButton = evt.getActionCommand();
		String correctionNext;
		if(!quizButton.equals("NEXT"))
        {
            System.out.println("Take an answer!");
			prevButton = quizButton;
            nextPress = true;
            next.setEnabled(true);
        }
		else
		{
            if(qPbG.getSelection() != null)
            {
                if(nextPress)
                {
                    System.out.println("nextPress is true");
                    correctionNext = scanQuiz.nextLine();
                    System.out.println(correctionNext);
                    System.out.println(quizButton);
                    if(prevButton.equals(correctionNext))
                    {
                        System.out.println("correct!");
                        rightOrWrong.setText("Correct!");
                        qPgD.addEnergy(3);
                    }
                    else
                    {
                        System.out.println("wrong!");
                        correctionNext = scanQuiz.nextLine();
                        rightOrWrong.setText(correctionNext);
                    }
                    for(int d = 0; d < 4; ++d)
                        answers[d].setEnabled(false);
                    nextPress = false;
                }
                else
                {
					qPgD.setResponse(true);
                    System.out.println("Reset!");
                    scanQuiz = qPgD.makeScanner("ecoQuiz.txt");
                    qPcards.show(qPeHH, "Game");
                    nextPress = false;
                    next.setEnabled(false);
                     for(int d = 0; d < 4; ++d)
                        answers[d].setEnabled(true);
                    rightOrWrong.setText("");
                    resetQuiz();
                }
            }
            else
            {
                System.out.println("Reset!");
                nextPress = false;
                next.setEnabled(false);
                rightOrWrong.setText("Please put in an answer!");
                repaint();
            }
		}
	}
}

//Allows the user to change temperature.(Not working on)
class TemperaturePage extends JPanel implements ActionListener
{
        private ButtonGroup tempGroup, sunGroup, humidGroup;
        private JButton[] pressTemp, pressSun, pressHumid;
        private int tempNow, humidNow, sunNow;//The current int for each condition.
        private GetData tPgD;//The GetData object.
        public TemperaturePage(GetData tPgDin)
        {
            pressTemp = pressSun = pressHumid = new JButton[3];
            tempGroup = sunGroup = humidGroup = new ButtonGroup();
            tempNow = humidNow = sunNow = 1;
            tPgD = tPgDin;
            setBackground(Color.MAGENTA);
             setLayout( new GridLayout(4,1));
            JLabel conditionTitle = new JLabel("Conditions");
            JPanel temperature = makeConditions("temperature", pressTemp, tempGroup);
            JPanel humidity = makeConditions("humidity", pressHumid, humidGroup);
            JPanel sunlight = makeConditions("sunlight", pressSun, sunGroup);
            add(conditionTitle);
            add(temperature);
            add(humidity);
            add(sunlight);
        }
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
        }

        //Makes each panel for TemperaturePage, creates buttons and sets
        //Listeners, adds buttons to panel, etc.
        public JPanel makeConditions(String condIn, JButton[] pressCond, ButtonGroup condGroup)
        {
            String condInLetter = condIn.substring(0,1);
            JPanel condPanel = new JPanel();
            condPanel.setLayout( new GridLayout(1,4));
            JLabel condLabel = new JLabel(condIn);
            condPanel.add(condLabel);
            for(int i = 0; i < 3; ++i)
            {
                pressCond[i] = new JButton(condInLetter + (i + 1));
                pressCond[i].addActionListener(this);
                condGroup.add(pressCond[i]);
                condPanel.add(pressCond[i]);
            }
            return condPanel;
        }

        public void actionPerformed(ActionEvent evt)
        {
			if(!tPgD.getTempChanging())
			{
				tPgD.setTempChanging(true);
				tPgD.subOrgs();
			}
            String condButtonName = evt.getActionCommand();
            if(condButtonName.substring(0,1).equals("t"))
            {
                tempNow = Integer.parseInt(condButtonName.substring(1,2))-1;
                tPgD.setTemp(condButtonName);
            }    
            else if(condButtonName.substring(0,1).equals("h"))
            {
                humidNow = Integer.parseInt(condButtonName.substring(1,2))-1;
                tPgD.setHumidity(condButtonName);
            }
            else
            {
                sunNow = Integer.parseInt(condButtonName.substring(1,2))-1;
                tPgD.setSunlight(condButtonName);
             }
       }

}

//Settings and Instructions at the same time, creates buttons for nav
//on left and chooses between settings/instructions on right.
class SetAndInstructPage extends JPanel implements ActionListener, ChangeListener, AdjustmentListener
{
    private JTextField valueReader;
    private JSlider yearSlide;
    private GetData sAIgD;
	private boolean which;//Determines settings or instructions.
	private CardLayout sAIcards;//Passed in cards. (switch cards)
	private EcoHausHolder sAIeHH;//Passed in EcoHausHolder. (switch cards)
	public SetAndInstructPage(boolean whichIn, GetData sAIgDin)
	{
        sAIgD = sAIgDin;
		which = whichIn;
		sAIcards = sAIgD.getCards();
		sAIeHH = sAIgD.geteHH();
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
        
		NavPage sAInP = new NavPage(sAIcards, sAIeHH);
		setInstBar.add(sAInP, BorderLayout.SOUTH);
		setInstBar.add(homePlayBarButton, BorderLayout.CENTER);
		add(setInstBar, BorderLayout.WEST);
        
		JLabel setOrInstruct;
		JPanel setIntAreHere;
		if(which)
		{
			 setOrInstruct = new JLabel("Settings");
			 setIntAreHere = new JPanel();
             setIntAreHere.setLayout(new GridLayout(3,2));
             
             JLabel setYears = new JLabel("# of years to play");
             setIntAreHere.add(setYears);
             yearSlide = new JSlider(JSlider.HORIZONTAL, 3, 12, 7);
             yearSlide.setPaintTicks(true);
             yearSlide.setPaintLabels(true);
             yearSlide.setMajorTickSpacing(1);
             yearSlide.setLabelTable( yearSlide.createStandardLabels(1));
             yearSlide.addChangeListener(this);
             setIntAreHere.add(yearSlide);
             
             JLabel setOrgs = new JLabel("# of organisms before year passes");
             setIntAreHere.add(setOrgs);
             JPanel orgBarHolder = new JPanel();
             orgBarHolder.setLayout(new BorderLayout());
             JScrollBar orgBar = new JScrollBar(JScrollBar.HORIZONTAL, 3, 1, 1, 5);
             orgBar.addAdjustmentListener(this);
             JPanel valueHolder = new JPanel();
             valueReader = new JTextField();
             valueReader.setEnabled(false);
             valueHolder.add(valueReader);
             orgBarHolder.add(orgBar, BorderLayout.NORTH);
             orgBarHolder.add(valueReader, BorderLayout.CENTER);
             setIntAreHere.add(orgBarHolder);

             JLabel setCond = new JLabel("Set beginning conditions");
             setIntAreHere.add(setCond);
             TemperaturePage sAItP = new TemperaturePage(sAIgD);
             setIntAreHere.add(sAItP);
			 add(setIntAreHere, BorderLayout.CENTER);
		}
		else
			setOrInstruct = new JLabel("Instructions");
		setOrInstruct.setBackground(Color.YELLOW);
		setInstBar.add(setOrInstruct, BorderLayout.NORTH);
		if(sAIgD.getGamePlaying())
		{
			sAIHome.setEnabled(false);
			sAIPlay.setEnabled(false);
			sAInP.disableButtons();
		}
	}
	public void actionPerformed(ActionEvent evt)
	{
		String sAIname = evt.getActionCommand();
		if(sAIname.equals("Play") && sAIgD.getResponse())
			sAIcards.show(sAIeHH, "Game");
		else
			sAIcards.show(sAIeHH, sAIname);
	}

	public void paintComponent(Graphics g)
	{
        Font instruct = new Font("Sans Serif", Font.PLAIN, 15);
		super.paintComponent(g);
		if(!which)
		{
			g.drawString("If you want to set settings for yourself, you"
                +"can do that in the settings panel.", 200, 100);
			g.drawString("Once you press play, you need to answer the "+
                "question that pops up to gain energy ", 200, 120);
			g.drawString("points, then add creatures from the bottom part"
                +" by pressing the + button and clicking on the habitat.", 200, 140);
            g.drawString("Add your name into your high score by typing "+
                "your name into the textField and entering it.", 200, 160);
            g.drawString("To stop adding creatures, click the + button again.", 200, 180);
			g.drawString("Organisms react to set conditions, and generate"
               +" energy points, as well as simulating a food chain.",200, 200);
			g.drawString("You get to put down the number of organisms you" 
                +" set in settings before time passes ", 200, 220);
			g.drawString("and organisms begin to be eaten. You have as"+
                "many time passes as the year you set in settings.", 200, 240);
            g.drawString("See what part of the food chain your organism "+
                "is and their preferred conditions by pressing info.", 200, 260);
			g.drawString("Set conditions in the top, but beware of natural"+
                " disasters that change them!", 200, 280);
            g.drawString("Each creature generates a different amount of "+
                "energy(seen in info), the more species, the higher energy.", 200, 300);
			g.drawString("Get a good score!", 200, 320);
		}
	}
    
	public void stateChanged(ChangeEvent evt)
	{
		if(!sAIgD.getResponse())
			sAIgD.setYearsToPlay(yearSlide.getValue());
		else
			yearSlide.setEnabled(false);
			
	}

    public void adjustmentValueChanged(AdjustmentEvent evt)
    {
		if(!sAIgD.getResponse())
		{
			sAIgD.setOrgToAdd(evt.getValue());
			sAIgD.setOrgsLeft();
			valueReader.setText("You will play for "+ evt.getValue() +" years/turns");
		}
		else
			valueReader.setText("Game is currently being played, no changing turns per year.");
    }

}

