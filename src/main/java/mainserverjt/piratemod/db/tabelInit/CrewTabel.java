package mainserverjt.piratemod.db.tabelInit;

public class CrewTabel {

	public static final String CREATE_SQL = 
	"CREATE TABLE crew (" +
	"id int(11) NOT NULL," +
	"naam varchar(255) NOT NULL,"+			
	"rank int(11) NOT NULL," +
	"gem_xp_lvl double NOT NULL," +
	"funding double NOT NULL," +
	"stichter_uuid varchar(40) NOT NULL" +
	") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
	
	public static final String INSERT_DUMMY_CERW =
			"INSERT INTO `crew` (`id`, `naam`, `rank`, `gem_xp_lvl`, `funding`, `stichter_uuid`) VALUES (-1, 'DUMMY', 0, 0, 0, 'DUMMY');";
	
	public static final String ALTER_TABLE_SETINGS=
			"ALTER TABLE `crew`"+
			"ADD PRIMARY KEY (`id`),"+
			"ADD UNIQUE KEY `naam` (`naam`);";
}
