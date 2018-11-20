-- (1) Write MySQL query to find IPs that mode more than a certain number of requests for a given time period.
SELECT ip, COUNT(ip) FROM log 
WHERE date between '2017-01-01 15:00:00' AND '2017-01-01 15:10:00'
group by ip
having  COUNT(ip) > 41;


-- (2) Write MySQL query to find requests made by a given IP.
SELECT date, status, userAgent FROM log WHERE ip = '192.168.106.134';

SELECT COUNT(*) FROM log