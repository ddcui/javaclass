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

import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
TemperaturePage tP = new TemperaturePage();
SetAndInstructPage sP = new SetAndInstructPage(true, gD);
add(sP, "Settings");
SetAndInstructPage iP = new SetAndInstructPage(false, gD);
add(iP, "Instructions");
GamePage gP = new GamePage(gD, qP, tP);
add(gP, "Game");
gP.setBackground(Color.YELLOW);
}
}

class GetData
{
private CardLayout dataCards;
private EcoHausHolder dataEcoHausHolder;
private boolean dataGamePlaying;
private NavPage dataNP;
private boolean addCreature;
private String whichCreature;
private boolean infoCreature;

public GetData()
{
dataGamePlaying = false;
}

public void setCards(CardLayout dataCardsIn)
{
dataCards = dataCardsIn;
}

public CardLayout getCards()
{
return dataCards;
}

public void seteHH(EcoHausHolder dataEcoHausHolderIn)
{
dataEcoHausHolder = dataEcoHausHolderIn;
}

public EcoHausHolder geteHH()
{
return dataEcoHausHolder;
}

public void setNP(NavPage dataNPIn)
{
dataNP = dataNPIn;
System.out.println("navPage set");
}

public NavPage getNP()
{
System.out.println("get navPage called");
return dataNP;
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
}

class NavPage extends JPanel implements ActionListener
{
private CardLayout nPcards;
private EcoHausHolder nPeHH;
private JButton settings, instructions, exit;

public NavPage(CardLayout nPcardsIn, EcoHausHolder nPeHHIn)
{
setLayout(new GridLayout(3,1));
nPcards = nPcardsIn;
nPeHH = nPeHHIn;
setBackground(Color.GREEN);
JPanel settingsPanel = new JPanel();
JPanel instructionsPanel = new JPanel();
JPanel exitPanel = new JPanel();
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

class HomePage extends JPanel implements ActionListener
{
private CardLayout hPcards;
private EcoHausHolder hPeHH;

public HomePage(GetData hPgD)
{
setLayout(null);
setBackground(Color.GREEN);
hPcards = hPgD.getCards();
hPeHH = hPgD.geteHH();

Font homeFont = new Font("Serif", Font.ITALIC, 50);
JLabel welcomeTo = new JLabel("Welcome to");
JLabel ecoHaus = new JLabel("Ecohaus");
NavPage hPnP = new NavPage(hPcards, hPeHH); //NavPage hPnP = hPgD.getNP();
System.out.println("navPage added to HomePage");
//~ JPanel hPnPholder = new JPanel();
//~ hPnPholder.setBackground(Color.ORANGE);
//~ hPnPholder.add(hPnP);
add(hPnP);//holder);
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
public HighScorePage(GetData hSPgD)
{
setLayout(new BorderLayout());
hSPcards = hSPgD.getCards();
hSPeHH = hSPgD.geteHH();
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
private GetData gPgD;
public GamePage(GetData gPgDin, QuizPage gPqP, TemperaturePage gPtP)
{
addMouseListener(this);
addKeyListener(this);
boolean gPgamePlaying = true;
gPgD = gPgDin;
gPgD.setGamePlaying(gPgamePlaying);
gPcards = gPgD.getCards();
gPeHH = gPgD.geteHH();
NavPage gPnP = gPgD.getNP();
GameMainFrame gMF = new GameMainFrame(gPgD);
Organism organismOne = new Organism("wildGrass");
Organism organismTwo = new Organism("antelope");
Organism organismThree = new Organism("muskrat");
Organism organismFour = new Organism("wolverine");
setLayout(new BorderLayout());
JPanel upperSection = new JPanel();
upperSection.setLayout(new BorderLayout());
upperSection.add(gPtP, BorderLayout.EAST);
add(upperSection, BorderLayout.NORTH);
JPanel mainGamePanel = new JPanel();
mainGamePanel.setLayout(new BorderLayout());
JPanel lowerSection = new JPanel();
lowerSection.setLayout(new BorderLayout());
JPanel gameInfo = new JPanel();
gameInfo.setLayout(new BorderLayout());
gameInfo.add(gPnP, BorderLayout.SOUTH);
lowerSection.add(gameInfo, BorderLayout.WEST);
lowerSection.add(mainGamePanel, BorderLayout.CENTER);
JPanel manageOrg = new JPanel();
manageOrg.setLayout(new GridLayout(1,4));
manageOrg.add(organismOne);
manageOrg.add(organismTwo);
manageOrg.add(organismThree);
manageOrg.add(organismFour);
mainGamePanel.add(gMF, BorderLayout.CENTER);
mainGamePanel.add(manageOrg, BorderLayout.SOUTH);
add(lowerSection, BorderLayout.CENTER);
}

public void actionPerformed(ActionEvent evt)
{
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

//THIS CLASS NEEDS THE IMAGES ASAP
class GameMainFrame extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
private int mouseX, mouseY, numOfOrgs;
private int[] creatureX, creatureY, chooseCreature;
private Timer creatureTimer;
private Image[] allOrgs;
private String[] orgNames;
private GetData gMFgD;
public GameMainFrame(GetData gMFgDin)
{
gMFgD = gMFgDin;
allOrgs = new Image[4];
orgNames = new String[]{"wildGrass.jpg","antelope.jpg","muskrat.jpg","wolverine.jpg"};
numOfOrgs = 0;
creatureX = new int[20];
creatureY = new int[20];
chooseCreature = new int[20];
setBackground(Color.CYAN);
creatureTimer = new Timer(50, this);
creatureTimer.start();
drawAllImg();
}

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

public void paintComponent(Graphics g)
{
	super.paintComponent(g);
	int xTop, xLow, yTop, yLow, moveCreature;
	xTop = this.getWidth() - 50;
	xLow = yLow = 50;
	yTop = this.getHeight() - 50;
	for(int x = 0; x < numOfOrgs; ++x)
	{
		moveCreature = (int)(Math.random()*2+0);
		if(moveCreature == 1)
		   creatureX[x]+=3;
		else if(moveCreature == 2)
			creatureX[x]-=3;
		moveCreature = (int)(Math.random()*2+0);
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
	for(int y = 0; y < numOfOrgs; ++y)
	{
			g.drawImage(allOrgs[chooseCreature[y]], creatureX[y], creatureY[y],50, 50, this);
	}
}

public int[] getArray(int[] expandArray)
{
int[] bigArray = new int[expandArray.length + 30];
for(int a = 0; a < expandArray.length; ++a)
bigArray[a] = expandArray[a];
return bigArray;
}
public void actionPerformed(ActionEvent evt)
{
repaint();
requestFocusInWindow();
}
public void mousePressed( MouseEvent evt )
{
	if(gMFgD.getAddCreature())
	{
		requestFocusInWindow();
		creatureX[numOfOrgs] = mouseX;
		creatureY[numOfOrgs] = mouseY;
		if(numOfOrgs == creatureX.length)
		{
		creatureX = getArray(creatureX);
		creatureY = getArray(creatureY);
		}
		if(gMFgD.getWhichCreature().equals("wildGrass.jpg"))
			chooseCreature[numOfOrgs] = 1;
		else if(gMFgD.getWhichCreature().equals("antelope.jpg"))
			chooseCreature[numOfOrgs] = 2;
		else if(gMFgD.getWhichCreature().equals("muskrat.jpg"))
			chooseCreature[numOfOrgs] = 3;
		else if(gMFgD.getWhichCreature().equals("wolverine.jpg"))
			chooseCreature[numOfOrgs] = 4;
		numOfOrgs += 1;
		repaint();
	}
}
public void mouseReleased( MouseEvent evt ){}
public void mouseClicked( MouseEvent evt ){}
public void mouseEntered( MouseEvent evt ) {}
public void mouseExited( MouseEvent evt ){}
public void mouseMoved(MouseEvent evt)
{
	mouseX = evt.getX();
	mouseY = evt.getY();
}
public void mouseDragged(MouseEvent evt){}

}

class Organism extends JPanel implements ActionListener
{
private String organismName;
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
JButton infoCreature = new JButton("i");
infoCreature.addActionListener(this);
instAndAdd.add(infoCreature);
}

public void actionPerformed(ActionEvent evt)
{
String gPbuttonName = evt.getActionCommand();
if(gPbuttonName.equals("+"))
gPgD.setAddCreature(true);
else if(gPbuttonName.equals("i"))
gPgD.setInfoCreature(true);
gPgD.setWhichCreature(organismName + ".jpg");
}
}
   
}

class QuizPage extends JPanel implements ActionListener
{
private boolean questionAnswered;
private CardLayout qPcards;
private EcoHausHolder qPeHH;
public QuizPage(GetData qPgD)
{
qPcards = qPgD.getCards();
qPeHH = qPgD.geteHH();
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
}
public void actionPerformed(ActionEvent evt)
{
}
}

class SetAndInstructPage extends JPanel implements ActionListener, ChangeListener
{
private CardLayout sAIcards;
private EcoHausHolder sAIeHH;
private NavPage sAInP;
public SetAndInstructPage(boolean which, GetData sAIgD)
{
sAIcards = sAIgD.getCards();
sAIeHH = sAIgD.geteHH();
sAInP = sAIgD.getNP();
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
JPanel sAInPHolder = new JPanel();
sAInPHolder.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
sAInPHolder.add(sAInP);
setInstBar.add(sAInP, BorderLayout.SOUTH);
setInstBar.add(homePlayBarButton, BorderLayout.CENTER);
add(setInstBar, BorderLayout.WEST);
if(sAIgD.getGamePlaying())
{
sAIHome.setEnabled(true);
sAIPlay.setEnabled(true);
sAInP.disableButtons();
}
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

