package Demonstrations;

import net.ravendb.client.indexes.AbstractTransformerCreationTask;

public class OperationTransformer extends AbstractTransformerCreationTask
{
    public OperationTransformer()
    {
        this.transformResults = "from res in results select new { res = res, timeZone = TimeZoneInfo.FindSystemTimeZoneById(this.ParameterOrDefault(\"timeZone\", \"Hawaiian Standard Time\").Value<string>()) } into this1 select new {AccountId = this1.res.AccountId, Delta = this1.res.Delta, OperationTime = TimeZoneInfo.ConvertTime((DateTime)this1.res.OperationTime, this1.timeZone), }";
      //  this.transformResults = "from res in results select new { res = res, timeZone = TimeZoneInfo.FindSystemTimeZoneById(this.ParameterOrDefault(\"timeZone\", \"Hawaiian Standard Time\").Value<string>()) } into this1 select new { Company = this1.res.Company, Employee = this1.res.Employee, OrderedAt = TimeZoneInfo.ConvertTime(this1.res.OrderedAt, this1.timeZone), TimeZone = this1.timeZone.Id, LocalTimeZone = TimeZoneInfo.Local.Id, OriginalOrderAt = this1.res.OrderedAt }";
    }
}
