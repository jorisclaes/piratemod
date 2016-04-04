package mainserverjt.piratemod.db.tabelInit;

public class PirateTable {

	public static final String CREATE_SQL =
			"CREATE TABLE `pirate` (" +
					  "`uuid` varchar(40) NOT NULL,"+
					  "`crew_id` int(11) NOT NULL,"+
					  "`naam` varchar(255) NOT NULL,"+
					  "`rank` int(11) NOT NULL,"+
					  "`funding` double NOT NULL,"+
					  "`type` varchar(255) NOT NULL,"+
					  "`lvl` float NOT NULL,"+
					  "`xp` float NOT NULL"+
					") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
	
	public static final String ALTER_TABLE_SETTINGS =
			"ALTER TABLE `pirate`"+
			 "ADD PRIMARY KEY (`uuid`),"+
			  "ADD KEY `crew_id` (`crew_id`);";
	
	public static final String ALTER_ADD_PIAMRY_KEY=
			"ALTER TABLE `pirate`"+
			  "ADD CONSTRAINT `pirate_ibfk_1` FOREIGN KEY (`crew_id`) REFERENCES `crew` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;";
}
