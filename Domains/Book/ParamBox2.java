/*
 * ParamBox2.java
 */

import java.awt.*;
import java.awt.event.*;

/**
 * ParamBox2 is a Frame with sliders that enables a user to
 * interactively set motor schema gains for robot navigation.
 *
 * <A HREF="../../docs/copyright.html">Copyright</A>
 * (c)1999, 2000 Tucker Balch, Georgia Tech Research Corporation and CMU.
 *
 * @author Tucker Balch
 * @version $Revision: 1.1 $
 */


public class ParamBox2 extends Frame implements AdjustmentListener, 
		ActionListener
	{
	private Scrollbar mtggainscroll;
	private TextField mtggaintext;
	private	double mtggain = 1.0;

	private Scrollbar noisegainscroll;
	private TextField noisegaintext;
	private	double noisegain = 0.2;

	private Scrollbar avoidgainscroll;
	private TextField avoidgaintext;
	private	double avoidgain = 0.8; 

	private String lastbutton = "stop";
	private String operator = "vector sum";

	private TextField comboop;

	/**
	 * Construct a ParamBox2.
	 */
	public ParamBox2() 
		{
		super("Parameters");

		//System.out.println("ParamBox2");

		setLayout(new GridLayout(6,3));

		// some handy fonts
		Font plainfont    = new Font("", Font.PLAIN, 10);
		Font boldfont     = new Font("", Font.BOLD, 10);
		Font boldbigfont  = new Font("", Font.BOLD, 12);

		/*--- add titles ---*/
		TextField tf;
		tf = new TextField("ParamBox2");
		tf.setEditable(false);
		tf.setFont(boldbigfont);
		add(tf);

		tf = new TextField("         ");
		tf.setEditable(false);
		tf.setFont(boldbigfont);
		add(tf);

		tf = new TextField("Value");
		tf.setEditable(false);
		tf.setFont(boldbigfont);
		add(tf);

		/*--- set up move-to-goal scroll ---*/
		// title
		tf = new TextField("move-to-goal");
		tf.setEditable(false);
		tf.setFont(plainfont);
		add(tf);

		// scroll
        	mtggainscroll = 
			new Scrollbar(Scrollbar.HORIZONTAL,100,1,0,301);
		mtggainscroll.setSize(1000,20);
		mtggainscroll.addAdjustmentListener(this);
        	add(mtggainscroll);

		// text
		mtggaintext = new TextField("1.0",3);
		mtggaintext.setFont(plainfont);
		mtggaintext.setEditable(false);
		add(mtggaintext);

		/*--- set up noise scroll ---*/
		// title
		tf = new TextField("noise");
		tf.setEditable(false);
		tf.setFont(plainfont);
		add(tf);

		// scroll
        	noisegainscroll = 
			new Scrollbar(Scrollbar.HORIZONTAL,20,1,0,301);
		noisegainscroll.setSize(1000,20);
		noisegainscroll.addAdjustmentListener(this);
        	add(noisegainscroll);

		// text
		noisegaintext = new TextField("0.2",3);
		noisegaintext.setFont(plainfont);
		noisegaintext.setEditable(false);
		add(noisegaintext);

		/*--- set up avoid scroll ---*/
		// title
		tf = new TextField("avoid-obstacles");
		tf.setEditable(false);
		tf.setFont(plainfont);
		add(tf);

		// scroll
        	avoidgainscroll = 
			new Scrollbar(Scrollbar.HORIZONTAL,80,1,0,301);
		avoidgainscroll.setSize(1000,20);
		avoidgainscroll.addAdjustmentListener(this);
        	add(avoidgainscroll);

		// text
		avoidgaintext = new TextField("0.8",3);
		avoidgaintext.setFont(plainfont);
		avoidgaintext.setEditable(false);
		add(avoidgaintext);

		/*--- add combo buttons ---*/
		Button b;
		b = new Button("vector sum");
		b.addActionListener(this);
		add(b);
		b = new Button("winner-take-all");
		b.addActionListener(this);
		add(b);
		comboop = new TextField("vector sum",14);
		comboop.setFont(plainfont);
		comboop.setEditable(false);
		add(comboop);

		/*--- add activation buttons ---*/
		b = new Button("start");
		b.addActionListener(this);
		add(b);
		b = new Button("stop");
		b.addActionListener(this);
		add(b);
		b = new Button("reset");
		b.addActionListener(this);
		add(b);

		pack();
		show();
    		}

	/**
	 * Handle button pushes.
	 */
	public void actionPerformed(ActionEvent e)
		{
		//System.out.println(e.getActionCommand());
		String lb = e.getActionCommand();

		if (lb.equals("start") 
			|| lb.equals("stop")
			|| lb.equals("reset"))
			{
			lastbutton = lb;
			}
		else
			{
			comboop.setText(lb);
			operator = lb;
			}
		}

	/**
	 * Handle scrollbar adjustments.
	 */
	public void adjustmentValueChanged(AdjustmentEvent e)
		{
		int val; 
		double vald;

		val  = noisegainscroll.getValue();
		vald = (double) val / 100;
		noisegaintext.setText(""+vald);
		noisegain = vald;

		val  = mtggainscroll.getValue();
		vald = (double) val / 100;
		mtggaintext.setText(""+vald);
		mtggain = vald;

		val  = avoidgainscroll.getValue();
		vald = (double) val / 100;
		avoidgaintext.setText(""+vald);
		avoidgain = vald;
    		}

	/**
	 * Return the current move to goal gain.
	 */
	public double getmtggain()
		{
		return(mtggain);
		}

	/**
	 * Return the current noise gain.
	 */
	public double getnoisegain()
		{
		return(noisegain);
		}

	/**
	 * Return the current avoid obstacle gain.
	 */
	public double getavoidgain()
		{
		return(avoidgain);
		}

	/**
	 * Return the text of the last button push.
	 */
	public String getlastbutton()
		{
		return(lastbutton);
		}

	/**
	 * Return the operator in use
	 */
	public String getoperator()
		{
		return(operator);
		}

	/**
	 * Test the class.
	 */
	public static void main(String argv[])
		{
		ParamBox2 dc = new ParamBox2();
		}
	}
