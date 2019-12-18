# comss
Test analista Java
select metrics.metricName, AVG(metrics.metricValue) as Porcertaje , (select ciclos.ciclosName from ciclos where ciclos.idCiclos = metrics.ciclos_fk) as ciclos
from metrics 
where metrics.ciclos_fk in (select ciclos.idCiclos from ciclos where ciclos.version_fk IN 
(select version.idVersion from version where version.idVersion= 1007 and version.app_fk in 
	(select apps.idApp from apps where idApp = 1006)))group by metrics.metricName, metrics.ciclos_fk;