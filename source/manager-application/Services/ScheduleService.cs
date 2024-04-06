using manager_application.models;
using manager_application.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace manager_application.Services
{
    internal class ScheduleService
    {
        private readonly HttpClient _client = APIService.instance.GetHttpClient();
        private HttpResponseMessage _response;

        public async Task<Response<List<Schedule>>> GetAllSchedules()
        {
            try
            {
                _response = await _client.GetAsync("schedule");
                _response.EnsureSuccessStatusCode();
                if (_response.IsSuccessStatusCode)
                {
                    Response<List<Schedule>> list = await _response.Content.ReadAsAsync<Response<List<Schedule>>>();
                    return list;
                }
                return null;
            }
            catch (Exception ex) { throw new Exception(ex.Message, ex); }
        }
    }
}

