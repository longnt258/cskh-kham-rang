using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace manager_application.models
{
    public class CallingHistory
    {
        [JsonProperty(PropertyName = "phoneNumber")]
        public string PhoneNumber {  get; set; }
        [JsonProperty(PropertyName = "status")]
        public bool Status { get; set; }
        [JsonProperty(PropertyName = "description")]
        public string Description { get; set; }
        [JsonProperty(PropertyName = "startDate")]
        public string StartDate { get; set; }
        [JsonProperty(PropertyName = "endDate")]
        public string EndDate { get; set; }
        [JsonProperty(PropertyName = "userFullName")]
        public string UserFullName {  get; set; }
    }
}
