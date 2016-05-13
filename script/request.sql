# R1 (sure)

SELECT u.* FROM BookComment c, BookUser u WHERE (c.UID = u.UID) AND  (c.Score > 3) AND EXISTS(SELECT c2.* FROM BookComment c2 WHERE (c2.UID = 7) AND (c.EID = c2.EID) AND (c2.Score > 3)) GROUP BY c.UID HAVING COUNT(DISTINCT c.EID) > 2;

# R3 (sure)

SELECT e.EID FROM Establishment e WHERE (SELECT COUNT(*) FROM BookComment c WHERE c.EID = e.EID) <= 1;


# R4 (sure)

SELECT a.* FROM Administrator a, EstablishmentCreation ec WHERE ec.AID = a.AID AND NOT EXISTS(SELECT * FROM BookComment c WHERE c.UID = a.UID AND c.EID = ec.EID) GROUP BY(ec.AID);


# R5 (sure)

SELECT e.*, AVG(c.Score) AS AvgScore FROM BookComment c, Establishment e WHERE e.EID = c.EID AND c.EID IN (SELECT e.EID FROM Establishment e WHERE (SELECT COUNT(*) FROM BookComment c WHERE e.EID = c.EID ) >= 3 ) GROUP BY (c.EID) ORDER BY AvgScore;









#R6
select t.DID  from TagDescribe t 
select e.EID from Establishment e

#t.TagDescribe , AVG(c.score) as AvgScore from BookComment

where (select count(*) from TagDescribe t where t.EID = e.EID) 




