__author__ = 'Princejeet Singh Sandhu'

"""
 This method check if the year is a leap year or not
 @:param n It is the number of year which is to be checked
"""
def leapYear(n):
    if n % 400 == 0:
        return True
    if n % 100 == 0:
        return False
    if n % 4 == 0:
        return True
    else:
        return False

"""
 This method returns the number of days in a month.
 @:param month It is the month for which days are to be found.
 @:param year It is the year in which the month occurred.
"""
def daysInMonth(month,year):
    if month == 4 or month == 6 or month == 9 or month == 11:
    	return 30
    elif month==2:
        if leapYear(year):
   			  return 29
        else:
            return 28
    else:
        return 31
"""
 This method moves from one day to the next.
 @:param month It is the month for which days are to be found.
 @:param year It is the year in which the month occurred.
 @:param day It is the day next to which the day has to be returned
"""
def nextDay(year, month, day):
    if day < daysInMonth(month,year):
        return year, month, day + 1
    else:
        if month == 12:
            return year + 1, 1, 1
        else:
            return year, month + 1, 1

"""
 Returns True if year1-month1-day1 is before year2-month2-day2. Otherwise, returns False.
"""
def dateIsBefore(year1, month1, day1, year2, month2, day2):
    if year1 < year2:
        return True
    if year1 == year2:
        if month1 < month2:
            return True
        if month1 == month2:
            return day1 < day2
    return False
"""
 Returns the number of days between year1/month1/day1
 and year2/month2/day2. Assumes inputs are valid dates
 in Gregorian calendar.
"""
def daysBetweenDates(year1, month1, day1, year2, month2, day2):
    # program defensively! Add an assertion if the input is not valid!
    assert not dateIsBefore(year2, month2, day2, year1, month1, day1)
    days = 0
    while dateIsBefore(year1, month1, day1, year2, month2, day2):
        year1, month1, day1 = nextDay(year1, month1, day1)
        days += 1
    return days

def test():
    test_cases = [((2012,1,1,2012,2,28), 58),
                  ((2012,1,1,2012,3,1), 60),
                  ((2011,6,30,2012,6,30), 366),
                  ((2011,1,1,2012,8,8), 585 ),
                  ((1900,1,1,1999,12,31), 36523)]

    for (args, answer) in test_cases:
        result = daysBetweenDates(*args)
        if result != answer:
            print "Test with data:", args, "failed"
        else:
            print "Test case passed!"

test()
