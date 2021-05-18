package domein.controllers;

import java.time.chrono.ChronoLocalDate;
import java.util.List;

import domein.Contract;
import domein.Ticket;
import domein.beheerders.StatistiekBeheerder;



public class StatistiekController {

	private StatistiekBeheerder statistiekBeheerder;

	public StatistiekController() {
		this.statistiekBeheerder = new StatistiekBeheerder();
	}

	public void verwerkDataTicket(ChronoLocalDate startDatum, ChronoLocalDate eindDatum) {
		
		 statistiekBeheerder.verwerkDataTicket(startDatum, eindDatum);
		
	}
		public void verwerkDataContracten(ChronoLocalDate startDatum, ChronoLocalDate eindDatum) {
		
		 statistiekBeheerder.verwerkDataContracten(startDatum, eindDatum);
		
	}



}
