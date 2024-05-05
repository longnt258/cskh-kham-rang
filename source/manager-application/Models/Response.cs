using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace manager_application.Models
{
    public class Response<T>
    {
        [JsonProperty(PropertyName = "data")]
        public T data { get; set; }
        [JsonProperty(PropertyName = "message")]
        public string Message { get; set; }
        [JsonProperty(PropertyName = "status")]
        public int Status { get; set; }

        Response() { }
    }
}
