package game;

public class Move {

	private int x;
	private int y;
	private int value;
	public void Move() {
		x=y=-1;
		value=0;
	}
	public Move(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.value = -1;
	}

	public Move(int value)
	{
		this.x = -1;
		this.y = -1;
		this.value = value;
	}

	public Move(int x, int y, int value)
	{
		this.x = x;
		this.y = y;
		this.value = value;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getValue()
	{
		return value;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public void setValue(int value)
	{
		this.value = value;
	}
}
