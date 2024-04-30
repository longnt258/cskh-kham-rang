using manager_application.models;
using manager_application.Models;
using manager_application.Services;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;

namespace manager_application.UserControlls
{
    public partial class DentistPanel : UserControl
    {
        private readonly DentistService dentistAPI;
        private List<Dentist> dentistList;
        private List<Dentist> searchList;

        public DentistPanel()
        {
            InitializeComponent();
            searchList = new List<Dentist>();
            dentistAPI = new DentistService();
            InitView();
        }

        async void InitView()
        {
            dataGridView1.Rows.Clear();
            Response<List<Dentist>> res = await dentistAPI.GetAllDentist();
           
            if(res.Status == 1)
            {
                dentistList = res.data;
                for (int i= 0; i < dentistList.Count; i++)
                {
                    Dentist dentist = dentistList[i];
                    dataGridView1.Rows.Add(new object[] 
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

        private void BtnRefresh_Click(object sender, EventArgs e)
        {
            InitView();
        }

        private void DataGridView1_CellMouseClick(object sender, DataGridViewCellMouseEventArgs e)
        {
            if (e.Button == MouseButtons.Right && e.RowIndex >= 0)
            {
                // Select the row that was clicked on
                dataGridView1.ClearSelection();
                dataGridView1.Rows[e.RowIndex].Selected = true;
                dataGridView1.CurrentCell = dataGridView1.Rows[e.RowIndex].Cells[e.ColumnIndex];

                // Create a ContextMenu
                ContextMenu m = new ContextMenu();

                MenuItem menuItemEditDentist = new MenuItem("Edit");
                menuItemEditDentist.Click += MenuItemOpenEditorDentist_Click;
                m.MenuItems.Add(menuItemEditDentist);
                // Show the ContextMenu at the location of the mouse click
                m.Show(dataGridView1, dataGridView1.PointToClient(Cursor.Position));
            }
        }

        private void MenuItemOpenEditorDentist_Click(object sender, EventArgs e)
        {
            // Get the selected row
            int selectedRow = dataGridView1.CurrentCell.RowIndex;
            Dentist dentist = dentistList[selectedRow];
            new AddOrUpdateDentist(dentist).ShowDialog();
        }

        private void SeachBtn_Click(object sender, EventArgs e)
        {
            dataGridView1.Rows.Clear();
            searchList = dentistList.Where(dentist => dentist.FullName.ToLower().Contains(tbSeach.Text.ToLower())).ToList();
            for (int i = 0;i< searchList.Count;i++) 
            {
                dataGridView1.Rows.Add(new object[]
                        {
                        i.ToString(),
                        searchList[i].FullName,
                        searchList[i].StartDateTime.ToString(),
                        searchList[i].EndDateTime.ToString() ,
                        searchList[i].Status? "Đang hoạt động" : "Không hoạt động"
                        });
            }
        }

        private void BtnAdd_Click(object sender, EventArgs e)
        {
            new AddOrUpdateDentist().ShowDialog();
        }
    }
}
