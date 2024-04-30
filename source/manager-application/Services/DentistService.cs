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
    internal class DentistService
    {
        private readonly HttpClient _client = APIService.GetInstance().GetHttpClient();
        private HttpResponseMessage _response;

        public async Task<Response<List<Dentist>>> GetAllDentist()
        {
            try
            {
                _response = await _client.GetAsync("dentist");
                _response.EnsureSuccessStatusCode();
                if (_response.IsSuccessStatusCode)
                {
                    Response<List<Dentist>> list = await _response.Content.ReadAsAsync<Response<List<Dentist>>>();
                    return list;
                }
                return null;
            }
            catch (Exception ex) { throw new Exception(ex.Message, ex); }
        }


        public async Task<Response<Dentist>> Insert(Dentist dentist)
        {
            try
            {
                _response = await _client.PostAsJsonAsync("dentist/create", dentist);
                _response.EnsureSuccessStatusCode();
                if (_response.IsSuccessStatusCode)
                {
                    Response<Dentist> detist = await _response.Content.ReadAsAsync<Response<Dentist>>();
                    return detist;
                }
                return null;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message, ex);
            }
        }

        public async Task<Response<Dentist>> Update(int id, Dentist dentist)
        {
            try
            {
                _response = await _client.PostAsJsonAsync($"dentist/update?dentistId={id}", dentist);
                _response.EnsureSuccessStatusCode();
                if (_response.IsSuccessStatusCode)
                {
                    Response<Dentist> detist = await _response.Content.ReadAsAsync<Response<Dentist>>();
                    return detist;
                }
                return null;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message, ex);
            }
        }
    }
   
}
