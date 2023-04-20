import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer {
	private String name;

	private List<Rental> rentals = new ArrayList<Rental>();

	public Customer(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);

	}

	public int calDaysRented(Rental each) {
		long diff ;
			if (each.getStatus() == 1) { // returned Video
				diff = each.getReturnDate().getTime() - each.getRentDate().getTime();
			} else { // not yet returned
				diff = new Date().getTime() - each.getRentDate().getTime();
			}
			return (int) (diff / (1000 * 60 * 60 * 24)) + 1;
	}
	public double calEachCharge(Rental each, double eachCharge, int daysRented){
		if (each.getVideo().getPriceCode()== Video.REGULAR) {
			eachCharge += 2;
			if (daysRented > 2)
				eachCharge += (daysRented - 2) * 1.5;
		}else{
			eachCharge = daysRented * 3;
		}
		return eachCharge;
	}

	public int calEachPoint(Rental each, int eachPoint, int daysRented){
		eachPoint++;
			
		if ((each.getVideo().getPriceCode() == Video.NEW_RELEASE) ) 
			eachPoint++;
		
		if ( daysRented > each.getDaysRentedLimit() )
			eachPoint -= Math.min(eachPoint, each.getVideo().getLateReturnPointPenalty()) ;
		
		return eachPoint;
	}

	public StringBuilder calTotalPoint(int totalPoint){
		StringBuilder result = new StringBuilder();
		if ( totalPoint >= 10 ) {
			result.append("Congrat! You earned one free coupon\n");
		}
		if ( totalPoint >= 30 ) {
			result.append("Congrat! You earned two free coupon\n");
		}
		return result;
	}

	public String getReport() {
		String result = "Customer Report for " + getName() + "\n";

		List<Rental> rentals = getRentals();

		double totalCharge = 0;
		int totalPoint = 0;

		for (Rental each : rentals) {
			double eachCharge = 0;
			int eachPoint = 0 ;
			int daysRented = 0;

			daysRented = calDaysRented(each);

			eachCharge = calEachCharge(each, eachCharge, daysRented);
			
			eachPoint = calEachPoint(each, eachPoint, daysRented);

			result += "\t" + each.getVideo().getTitle() + "\tDays rented: " + daysRented + "\tCharge: " + eachCharge
					+ "\tPoint: " + eachPoint + "\n";

			totalCharge += eachCharge;

			totalPoint += eachPoint ;
		}

		result += "Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n";

		System.out.println(calTotalPoint(totalPoint));
		return result ;
	}
}
