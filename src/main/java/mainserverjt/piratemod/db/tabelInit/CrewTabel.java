package mainserverjt.piratemod.db.tabelInit;

public class CrewTabel {

	public static final String CREATE_SQL = 
	"IF NOT EXISTS CREATE TABLE 'crew' (" +
	"'id' int(11) NOT NULL," +
	"'rank' int(11) NOT NULL," +
	"'gem_xp_lvl' double NOT NULL," +
	"'funding' double NOT NULL," +
	"'stichter_uuid' varchar(40) NOT NULL" +
	") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
	
	public static final String ALTER_TABLE_SETINGS=
			"ALTER TABLE `crew`" +
			  "ADD PRIMARY KEY (`id`);";
}
