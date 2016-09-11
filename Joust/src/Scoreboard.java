//Patrick Anderson (psa5dg)
//Russell Green (rmg5qa)

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Scoreboard {

	private int score1;
	private int score2;
	private boolean gameOver;
	private ArrayList<Integer> highScores;
	private int highScoresCapacity;
	
	public Scoreboard()
	{
		this.score1 = 0;
		this.score2 = 0;
		this.gameOver = false;
		this.highScores = new ArrayList<Integer>();
		this.highScoresCapacity = 3;
	}
	public Scoreboard(int highScoresCapacity)
	{
		this.score1 = 0;
		this.score2 = 0;
		this.gameOver = false;
		this.highScores = new ArrayList<Integer>();
		this.highScoresCapacity = highScoresCapacity;
	}
	public void incrementScore1()
	{
		this.score1++;
	}
	public void incrementScore2()
	{
		this.score2++;
	}
	public int getScore1()
	{
		return this.score1;
	}
	public int getScore2()
	{
		return this.score2;
	}
	public boolean isGameOver()
	{
		return this.gameOver;
	}
	public void endGame()
	{
		this.gameOver = true;
	}
	public ArrayList<Integer> getHighScores()
	{
		return this.highScores;
	}
	public void addHighScore(int newScore)
	{
		this.highScores.add(newScore);
		Collections.sort(this.highScores, Collections.reverseOrder());
		while (this.highScores.size() > highScoresCapacity)
		{
			this.highScores.remove(this.highScores.size()-1);
		}
	}
	public void loadHighScores(String filename) throws Exception
	{
		File myFile = new File(filename);
		Scanner inputFile = new Scanner(myFile);
		String line = inputFile.nextLine();
		String[] cells = line.split(",");
		for (int i=0; i<cells.length; i++)
		{
			this.highScores.add(Integer.parseInt(cells[i]));
		}
		inputFile.close();
		Collections.sort(this.highScores, Collections.reverseOrder());
		while (this.highScores.size() > highScoresCapacity)
		{
			this.highScores.remove(this.highScores.size()-1);
		}
	}
	public void saveHighScores(String filename) throws Exception
	{
		FileWriter fwriter = new FileWriter(filename);
		PrintWriter outputFile = new PrintWriter(fwriter);
		for (int i=0; i<this.highScores.size(); i++)
		{
			outputFile.print(highScores.get(i));
			if (i < this.highScores.size()-1) outputFile.print(",");
		}
		outputFile.close();
	}
	public String toString()
	{
		String not = "";
		if (!isGameOver())
		{
				not = "not ";
		}
		String scores = "";
		for (int i=0; i<this.highScores.size(); i++)
		{
			scores += this.highScores.get(i);
			scores += ",";
		}
		if (!scores.equals("")) scores = scores.substring(0, scores.length()-1);
		String outp = this.score1+" - "+this.score2+". Game is "+not+"over. High scores: "+scores;
		return outp;
	}
	public void draw(Graphics g)
	{
		g.setColor(new Color(127,0,0));
		g.drawString(getScore1() + "", 30, 30);
		g.setColor(new Color(0,127,0));
		g.drawString(getScore2() + "", 760, 30);
	}
}
