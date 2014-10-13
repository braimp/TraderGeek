package com.musebrain.tradergeek;

import java.util.Random;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity{


	// Buttons from the Keypad
	Button button0;
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;
	Button button6;
	Button button7;
	Button button8;
	Button button9;
	Button buttonDot;
	Button buttonMinus;
	Button buttonEnter;
	Button buttonCancel;
	
	// TextViews from the problem displayed
	TextView equation;
	TextView answer;
	
	// Timer Variables
	CountDownTimer timer;
	
	//Player related stats
	double timeTaken = 0;
	double totalTime = 480;
	int playerScore = 0;
	int problemCount = 0;
	int numProblems = 80;
	
	// Private Variables
	private boolean endGameFlag = false;
	private Random rand = new Random();
		
	private String[] eqnDB = new String[80];
	private double[] eqnAnsDB = new double[80];
	
	private int populatorCount = 0;
	private int[] addSubParams = {100,100,100,250,250,100,100,100,100,100};
	private int[] mulDivParams = {50,50,80,50,50,80,80,75,71,71}; 
			
	private String mEquation = "";
	private String mAnswer = "";
	private double mIdealAnswer;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView startButton = (TextView) findViewById(R.id.startbutton);
		
		startButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setContentView(R.layout.calculator);
				
				button0 = (Button) findViewById(R.id.button_0);
				button1 = (Button) findViewById(R.id.button_1);
				button2 = (Button) findViewById(R.id.button_2);
				button3 = (Button) findViewById(R.id.button_3);
				button4 = (Button) findViewById(R.id.button_4);
				button5 = (Button) findViewById(R.id.button_5);
				button6 = (Button) findViewById(R.id.button_6);
				button7 = (Button) findViewById(R.id.button_7);
				button8 = (Button) findViewById(R.id.button_8);
				button9 = (Button) findViewById(R.id.button_9);
				buttonDot = (Button) findViewById(R.id.button_dot);
				buttonMinus = (Button) findViewById(R.id.button_minus);
				buttonEnter = (Button) findViewById(R.id.enter);
				buttonCancel = (Button) findViewById(R.id.cancel);
				
				equation = (TextView) findViewById(R.id.equation);
				answer = (TextView) findViewById(R.id.answer);
				generateEqnDB();
				generateNewEquation();
				updateScreenViews();
				startTimer();
				setListeners();
			} // End of onClick Method
		});// End of startButton onClick listener
			
	}// End of onCreate Method
	
	
	private void setListeners(){
		button0.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mAnswer += "0";
				updateAnswer();
			}
		});
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mAnswer += "1";
				updateAnswer();
			}
		});
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mAnswer += "2";
				updateAnswer();
			}
		});
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mAnswer += "3";
				updateAnswer();
			}
		});
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mAnswer += "4";
				updateAnswer();
			}
		});
		button5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mAnswer += "5";
				updateAnswer();
			}
		});
		button6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mAnswer += "6";
				updateAnswer();
			}
		});

		button7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mAnswer += "7";
				updateAnswer();
			}
		});

		button8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mAnswer += "8";
				updateAnswer();
			}
		});

		button9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mAnswer += "9";
				updateAnswer();
			}
		});
		

		buttonDot.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mAnswer += ".";
				updateAnswer();
			}
		});
		
		buttonMinus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mAnswer.startsWith("-")) mAnswer = mAnswer.substring(1);
				else mAnswer = "-" + mAnswer;
				updateAnswer();
			}
		});
		
		buttonCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mAnswer = "";
				updateAnswer();
			}
		});
		

		buttonEnter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try{
					if((Double.parseDouble(mAnswer) - mIdealAnswer) == 0) playerScore += 1;
					else playerScore -= 1;
					
					if(problemCount == numProblems){
						endGame();
					}else{
						generateNewEquation();
						updateScreenViews();
					}
					
				}catch(Exception e){
					Toast.makeText(MainActivity.this, 
								   "Enter a valid Answer", 
								   Toast.LENGTH_SHORT).show();
				}
				
			}// End of onclick method
		});// End of listener for enter button
		
		
		
	}
	
	
	
	private void generateEqnDB(){
		
		// Random instance for generating operation probabilistically
		// We have a bias of 2:1 for Addition to Multiplication opeartions
		Random randOp = new Random();
		
		for(int i=0;i<numProblems;i++){
			int operationRand = randOp.nextInt()%4;
			if(operationRand ==0 || operationRand ==1 || operationRand == 3) generateAddOrSub();
			else generateMulOrDiv();
		}
		
	}
	
	private void generateAddOrSub(){
		Random randOp2 = new Random();
		boolean isAdd = randOp2.nextBoolean();
		genSimple(isAdd);
	}
	
	private void generateMulOrDiv(){
		Random randOp2 = new Random();
		boolean isMul = randOp2.nextBoolean();
		genComplex(isMul);
	}
	
	private void genSimple(boolean isAdd){
		Random numRandGen = new Random();
		int numRandGenIndex = (numRandGen.nextInt(5))*2;
		int num1 = numRandGen.nextInt(addSubParams[numRandGenIndex]);
		int num2 = numRandGen.nextInt(addSubParams[numRandGenIndex+1]);
		Random floatOrInt = new Random();
		boolean isFloat = floatOrInt.nextBoolean();
	
		String eqn;
		double ans;
		if(isFloat){
			double num1DBL = genDouble(num1);
			double num2DBL = genDouble(num2);
			if(isAdd){
				eqn = num1DBL + "+" + num2DBL;
				ans = num1DBL + num2DBL;
			}else{
				eqn = num1DBL + " - " + num2DBL;
				ans = num1DBL - num2DBL;
			}
		}else{
			if(isAdd){
				eqn = num1 + "+" + num2;
				ans = num1 + num2;
			}else{
				eqn = num1 + " - " + num2;
				ans = num1 - num2;
			}
		}
		eqnDB[populatorCount] = eqn;
		eqnAnsDB[populatorCount] = ans;
		populatorCount++;	
	}
	
	private void genComplex(boolean isMul){
		
		Random numRandGen = new Random();
		int numRandGenIndex = (numRandGen.nextInt(5))*2;
		int num1 = numRandGen.nextInt(mulDivParams[numRandGenIndex]);
		int num2 = numRandGen.nextInt(mulDivParams[numRandGenIndex+1]);
		
		Random floatOrInt = new Random();
		boolean isFloat = floatOrInt.nextBoolean();
		
		String eqn;
		double ans;
		String ansStr = String.valueOf(num1*num2);
		int ansLenMax = ansStr.length() + 2;
		if(isFloat){
			double num1DBL = genDouble(num1);
			double num2DBL = genDouble(num2);
			if(isMul){
				eqn = num1DBL + " * " + num2DBL;
				ans = num1DBL * num2DBL;
			}else{
				double ansI = num1DBL*num2DBL;
				String ansIStr = String.format("%."+4+"f", ansI);
				eqn = ansIStr + " / " + num1DBL;
				ans = num2DBL;
			}
		}else{
			if(isMul){
				eqn = num1 + " * " + num2;
				ans = num1 * num2;
			}else{
				int ansI = num1*num2;
				eqn = ansI + " / " + num1;
				ans = num2;
			}
			
		}
		
		eqnDB[populatorCount] = eqn;
		eqnAnsDB[populatorCount] = ans;
		populatorCount++;
			
	}
	
	private double genDouble(int num){
		double ans = 0.0;
	
		// Code written with the point of view that digits don't exceed 9999
		String numStr = num + "";
		Random floatPoint = new Random();
		int pointPos = floatPoint.nextInt(numStr.length());
		switch(pointPos){
			case 0:
				ans = (1.0*num)/Math.round(Math.pow(10, numStr.length()));
				break;
			case 1:
				ans = (1.0*num)/Math.round(Math.pow(10, numStr.length()-1));
				break;
			case 2:
				ans = (1.0*num)/Math.round(Math.pow(10, numStr.length()-2));
				break;
			case 3:
				ans = (1.0*num)/Math.round(Math.pow(10, numStr.length()-3));
				break;
			default:
				ans = 0.0;
				break;
		}
		return ans;
		
	}

	
	private void generateNewEquation(){
		queryDatabaseAndGenerateEqn();
		mAnswer = "";	
		problemCount++;
	}
	
	private void queryDatabaseAndGenerateEqn(){
		int eqnIndex = problemCount;
		mEquation = eqnDB[eqnIndex];
		mIdealAnswer = eqnAnsDB[eqnIndex];
	}
	
	private void updateScreenViews(){
		updateEquation();
		updateAnswer();
	}// End of updateScreen Views method
	
	private void updateEquation(){
		adjustEquationSize();
		equation.setText(mEquation);
	}
	
	private void updateAnswer(){
		adjustAnswerSize();
		answer.setText(mAnswer);
	}
	
	private void adjustEquationSize(){
		if(mEquation.length()>=6)
			equation.setTextSize(24);
		if(mEquation.length()>=10)
			equation.setTextSize(23);
		if(mEquation.length()>=12)
			equation.setTextSize(22);
		if(mEquation.length()>=14)
			equation.setTextSize(20);
		if(mEquation.length()>=16)
			equation.setTextSize(18);
		if(mEquation.length()>=18)
			equation.setTextSize(16);
	}
	
	private void adjustAnswerSize(){
		if(mAnswer.length() >=2)
			answer.setTextSize(24);
		if(mAnswer.length() >=3)
			answer.setTextSize(23);
		if(mAnswer.length() >=4)
			answer.setTextSize(22);
		if(mAnswer.length() >=5)
			answer.setTextSize(20);
		if(mAnswer.length() >=6)
			answer.setTextSize(18);
		
	}
	
	
	private void startTimer(){
		int time = (int) totalTime*1000;
		timer = new CountDownTimer(time, 500){
			@Override
			public void onFinish() {
				endGame();
			}
			@Override
			public void onTick(long millisUntilFinished) {
				timeTaken = totalTime - millisUntilFinished/1000;
			}
		}.start();
	}// End of StartTimer Method
	
	private void endGame(){
		if(timer!= null){
			timer.cancel();
			timer = null;
		}
		
		setContentView(R.layout.scoreview);
		
		String boldMsgStr = "Your score is given below";
		String timeTakenStr = timeTaken + "";
		timeTakenStr = timeTakenStr.substring(0,3);
		String lightMsgStr = "You took a total time of " + timeTakenStr + " seconds";
		
		TextView boldMsg = (TextView) findViewById(R.id.boldmsg);
		TextView lightMsg = (TextView) findViewById(R.id.lightmsg);
		TextView playerScoreView = (TextView) findViewById(R.id.scoreButton);
		
		boldMsg.setText(boldMsgStr);
		lightMsg.setText(lightMsgStr);
		String playerScoreStr = playerScore + "";
		playerScoreView.setText(playerScoreStr);
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.main, menu);
	    MenuItem item = menu.findItem(R.id.share_button);
	    ShareActionProvider myShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
		 
	    Intent myIntent = new Intent();
	    myIntent.setAction(Intent.ACTION_SEND);
	    String shareMsg = " Hey, this is a message from the Trader Geek App." +
	    				  " Your friend chose to share this with you." +
	    				  " We are a pretty useful app for people aspiring to be Junior Traders. " +
	    				  " See if you can score 55+ on our test! Any feedback is always welcomed!" +
	    				  " - https://play.google.com/store/apps/details?id=com.musebrain.tradergeek";
	    String shareSub = "Trader Geek Android App";
	    myIntent.putExtra(Intent.EXTRA_TEXT, shareMsg);
	    myIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
	    myIntent.setType("text/plain");	 
	    myShareActionProvider.setShareIntent(myIntent);
		
		return true;
	}

}
