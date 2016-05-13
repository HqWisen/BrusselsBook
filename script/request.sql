# R1 (sure)

SELECT u.* FROM BookComment c, BookUser u WHERE (c.UID = u.UID) AND  (c.Score > 3) AND EXISTS(SELECT c2.* FROM BookComment c2 WHERE (c2.UID = 7) AND (c.EID = c2.EID) AND (c2.Score > 3)) GROUP BY c.UID HAVING COUNT(DISTINCT c.EID) > 2;

# R5 (sure)

select c.EID, AVG(c.Score) as AvgScore from BookComment c where c.EID in ( select e.EID from Establishment e where ( select count(*) from BookComment c where e.EID = c.EID ) >= 3 ) group by (c.EID) order by AvgScore;



#R6
select t.DID  from TagDescribe t 
select e.EID from Establishment e

#t.TagDescribe , AVG(c.score) as AvgScore from BookComment

where (select count(*) from TagDescribe t where t.EID = e.EID) 
  

# R3 (sure)

select e.EID from Establishment e where
(
select count(*) from BookComment c where c.EID = e.EID
) <= 1;

# R4 (sure)

SELECT a.* FROM Administrator a, EstablishmentCreation ec WHERE ec.AID = a.AID AND NOT EXISTS(SELECT * FROM BookComment c WHERE c.UID = a.UID AND c.EID = ec.EID) GROUP BY(ec.AID);


