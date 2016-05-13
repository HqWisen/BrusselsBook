# R1

SELECT u.* FROM BookComment c, BookUser u, BookUser ub
WHERE (c.UID = u.UID) AND  (c.Score > 3)
AND (u.Username <> 'Brenda') AND (ub.Username = 'Brenda')
AND EXISTS(SELECT c2.* FROM BookComment c2 WHERE (c2.UID = ub.UID)
AND (c.EID = c2.EID) AND (c2.Score > 3)) GROUP BY c.UID
HAVING COUNT(DISTINCT c.EID) > 2

#R3
SELECT * FROM Establishment e WHERE
(SELECT COUNT(*) FROM BookComment c WHERE c.EID = e.EID) <= 1
	
#R4	

SELECT u.* FROM Administrator a, EstablishmentCreation ec, BookUser u 
WHERE ec.AID = a.AID AND a.UID = u.UID "
AND NOT EXISTS(SELECT * FROM BookComment c
WHERE c.UID = a.UID AND c.EID = ec.EID)
GROUP BY(ec.AID)

#R5	

SELECT e.*, AVG(c.Score)
AS AvgScore FROM BookComment c, Establishment e WHERE
e.EID = c.EID AND c.EID IN
(SELECT e.EID FROM Establishment e WHERE
(SELECT COUNT(*) FROM BookComment c WHERE e.EID = c.EID ) >= 3 )
GROUP BY (c.EID) ORDER BY AvgScore

#R6
SELECT td.TagName, (SELECT AVG(c.Score)
FROM BookComment c, TagDescribe td2
WHERE (td.TagName = td2.TagName) AND (c.EID = td2.EID)) as AvgScore
FROM BookComment c, TagDescribe td
GROUP BY td.TagName HAVING COUNT(DISTINCT td.EID) > 4 ORDER BY AvgScore

