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
    public partial class CustomerPanel : UserControl
    {
        private readonly CustomerService customerApi;

        public CustomerPanel()
        {
            InitializeComponent();
            customerApi = new CustomerService();
            InitView();
        }

        async void InitView()
        {

            dataGridView1.Rows.Clear();
            Response<List<Customer>> res = await customerApi.GetAllCustomer();
            if (res.Status == 1)
            {
                for (int i = 0; i < res.data.Count; i++)
                {
                    Customer customer = res.data[i];
                    dataGridView1.Rows.Add(new object[]
                    {
                        i.ToString(),
                        customer.FullName,
                        customer.Email,
                        customer.PhoneNumber
                    });
                }
            }
        }

        private void BtnRefresh_Click(object sender, EventArgs e)
        {
            InitView();
        }

    }
}
