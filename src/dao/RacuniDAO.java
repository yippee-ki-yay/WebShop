package dao;

import model.Racun;

public class RacuniDAO extends GenericDAO<Racun>
{

	public RacuniDAO()
	{
		openDb("racuni");
		
		readFile();
	}
}
