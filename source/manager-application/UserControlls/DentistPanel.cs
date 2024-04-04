using manager_application.models;
using manager_application.Models;
using manager_application.Services;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace manager_application.UserControlls
{
    public partial class DentistPanel : UserControl
    {
        private DentistAPI dentistAPI;

        public DentistPanel()
        {
            InitializeComponent();
            dentistAPI = new DentistAPI();
            InitView();
        }

        async void InitView()
        {
            dataGridView1.Rows.Clear();
            Response<List<Dentist>> res = await dentistAPI.GetAllDentist(); 
            if(res.Status == 1) 
            {
                dataGridView1.Rows.Clear();
                for (int i= 0; i < res.data.Count; i++)
                {
                    Dentist dentist = res.data[i];
                    dataGridView1.Rows.Add(new Object[] 
                    { 
                        i.ToString(),
                        dentist.FullName,
                        dentist.StartDateTime.ToString(),
                        dentist.EndDateTime.ToString() ,
                        dentist.Status? "Đang hoạt động" : "Không hoạt động"
                    });
                }
            }

        }

        private void btnRefresh_Click(object sender, EventArgs e)
        {
            InitView();
        }
    }
}
