using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace manager_application.Models
{
    internal class Response<T>
    {
        public Response() { }
        public T data { get; set; }
        public string Message { get; set; }
        public int Status { get; set; }
    }
}
