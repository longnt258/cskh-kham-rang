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
    internal class HistoryCallService
    {
        private readonly HttpClient _client = APIService.GetInstance().GetHttpClient();
        private HttpResponseMessage _response;


        public async Task<Response<List<CallingHistory>>> GetAll()
        {
            try
            {
                _response = await _client.GetAsync("calling-history");
                _response.EnsureSuccessStatusCode();
                if(_response.IsSuccessStatusCode )
                {
                    Response<List<CallingHistory>> list = await _response.Content.ReadAsAsync<Response<List<CallingHistory>>>();
                    return list;
                }
                else
                {
                    return null;
                }
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message, ex);
            }
        }
    }

}
