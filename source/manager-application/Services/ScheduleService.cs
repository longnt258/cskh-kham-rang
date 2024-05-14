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
        private readonly HttpClient _client = APIService.GetInstance().GetHttpClient();
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

        public async Task<Response<Schedule>> insert(Schedule schedule)
        {
            try
            {
                _response = await _client.PostAsJsonAsync("schedule/create-by-admin", schedule);
                _response.EnsureSuccessStatusCode();
                if(_response.IsSuccessStatusCode)
                {
                    Response<Schedule> response =  await _response.Content.ReadAsAsync<Response<Schedule>>();
                    return response;
                }
                return null;
            }
            catch (Exception ex) { throw new Exception(ex.Message, ex); }
        }

        public async Task<Response<Schedule>> update(Schedule schedule)
        {
            try
            {
                _response = await _client.PostAsJsonAsync($"schedule/update?scheduleCode={schedule.Code}", schedule);
                _response.EnsureSuccessStatusCode();
                if(_response.IsSuccessStatusCode)
                {
                    Response<Schedule> response = await _response.Content.ReadAsAsync<Response<Schedule>>();
                    return response;
                }
                return null;
            }
            catch (Exception ex) { throw new Exception(ex.Message, ex); }
        }
    }
}

