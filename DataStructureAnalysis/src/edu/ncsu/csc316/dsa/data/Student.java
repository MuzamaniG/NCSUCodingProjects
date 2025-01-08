package edu.ncsu.csc316.dsa.data;

import java.util.Objects;

	/**
	 * A student is comparable and identifiable.
	 * Students have a first name, last name, id number, 
	 * number of credit hours, gpa, and unityID.
	 * 
	 * @author Dr. King
	 * @author mhgausi
	 *
	 */
	public class Student implements Comparable<Student>, Identifiable {
		
		/** Student's first name */
		private String first;
		/** Student's last name */
	    private String last;
	    /** Student's id number */
	    private int id;
	    /** Student's number of credit hours */
	    private int creditHours;
	    /** Student's GPA */
	    private double gpa;
	    /** Student's unity ID */
	    private String unityID;
	
	    /**
	     * Constructor for Student object
	     * @param first name of student
	     * @param last name of student
	     * @param id number of student
	     * @param creditHours number of student credit hours
	     * @param gpa of student
	     * @param unityID of student
	     */
	    public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
	        setFirst(first);
	        setLast(last);
	        setId(id);
	        setCreditHours(creditHours);
	        setGpa(gpa);
	        setUnityID(unityID);
	    }
	
	    /**
	     * Getter for student first name
	     * @return String first name of student
	     */
	    public String getFirst() {
	        return first;
	    }
	
	    /**
	     * Setter for student's first name
	     * @param first name of student
	     */
	    public void setFirst(String first) {
	        this.first = first;
	    }
	
	    /**
	     * Getter for last name of student
	     * @return last name of student
	     */
	    public String getLast() {
	        return last;
	    }
	
	    /**
	     * Setter for last name of student
	     * @param last name of student
	     */
	    public void setLast(String last) {
	        this.last = last;
	    }
	
	    /**
	     * Getter for student's id
	     * @return id of student
	     */
	    public int getId() {
	        return id;
	    }
	
	    /**
	     * Setter for student's id
	     * @param id of student
	     */
	    public void setId(int id) {
	        this.id = id;
	    }
	
	    /**
	     * Getter for student's number of credit hours
	     * @return creditHours of student
	     */
	    public int getCreditHours() {
	        return creditHours;
	    }
	
	    /**
	     * Setter for number of student credit hours
	     * @param creditHours number of student credit hours
	     */
	    public void setCreditHours(int creditHours) {
	        this.creditHours = creditHours;
	    }
	
	    /**
	     * Getter for student's GPA
	     * @return gpa of student
	     */
	    public double getGpa() {
	        return gpa;
	    }
	
	    /**
	     * Setter for student's gpa
	     * @param gpa of student
	     */
	    public void setGpa(double gpa) {
	        this.gpa = gpa;
	    }
	
	    /**
	     * Getter for student's unity ID
	     * @return unityID of student
	     */
	    public String getUnityID() {
	        return unityID;
	    }
	
	    /**
	     * Setter for student's unity ID
	     * @param unityID of student
	     */
	    public void setUnityID(String unityID) {
	        this.unityID = unityID;
	    }
	
	    /**
	     * Returns student hash code
	     * Overrides other existing hashCode methods
	     */
	    @Override
		public int hashCode() {
			return Objects.hash(creditHours, first, gpa, id, last, unityID);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Student other = (Student) obj;
			return this.first.equals(other.first) && this.last.equals(other.last)
					&& this.id == other.id;
		}
	
	    /**
	     * Compares Student object by checking whether last name, first name,
	     * and unity id are equal in that order.
	     * @param other Student being compared to
	     * @return int value based on whether student has less than, greater than, or
	     * equal value based on last name, first name, and id
	     */
	    @Override
	    public int compareTo(Student other) {
	        if (this.last.compareTo(other.last) != 0) { 
	            return this.last.compareTo(other.last);
	        }
	        else if (this.first.compareTo(other.first) != 0) {
	        	return this.first.compareTo(other.first);
	        }
	        else {
	        	return Integer.compare(this.id, other.id);
	        }
	    }
	
	    /**
	     * Creates string representation of student object,
	     * containing first name, last name, id number, number of 
	     * credit hours, GPA, and unity ID. 
	     * Overrides other toString methods
	     * 
	     * @return String representation of Student object
	     */
	    @Override
	    public String toString() {
	        return "[" + first + ", " + last + ", " + id + ", " + creditHours + ", "
	                + gpa + ", " + unityID + "]";
	    }
	}
