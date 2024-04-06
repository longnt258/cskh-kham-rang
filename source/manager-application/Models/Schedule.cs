using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace manager_application.models
{
    internal class Schedule
    {
        public string Code { get; set; }
        public string Title {  get; set; }
        public string Description { get; set; }
        public string BookDateTime { get; set; }
        public string DentistName {  get; set; }
        public string UserFullName {  get; set; }
    }
}
