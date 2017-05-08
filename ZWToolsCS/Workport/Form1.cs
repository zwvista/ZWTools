using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using mshtml;

namespace Workport
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            webBrowser1.ScriptErrorsSuppressed = true;
        }

        private void webBrowser1_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {
            if (webBrowser1.ReadyState != WebBrowserReadyState.Complete) return;
            var s = webBrowser1.Url.AbsoluteUri;

            if (s.StartsWith("https://wpct.workport.co.jp/progress.php"))
            {
                var doc = (IHTMLDocument2)webBrowser1.Document.DomDocument;
                var item = (from IHTMLElement elem in doc.all
                            where elem is HTMLAnchorElement && elem.innerText == "応募したい"
                            select elem).FirstOrDefault();
                item.click();
            }
            else if (s.StartsWith("https://wpct.workport.co.jp/cnmsg.php"))
            {
                var doc = (IHTMLDocument2)webBrowser1.Document.DomDocument;
                var item = (from IHTMLElement elem in doc.all
                            where elem is HTMLInputButtonElement && elem.getAttribute("value") == "送信"
                            select elem).FirstOrDefault();
                item.click();
            }
            else if (s.StartsWith("https://wpct.workport.co.jp/cnsend.php"))
            {
                var doc = (IHTMLDocument2)webBrowser1.Document.DomDocument;
                var item = (from IHTMLElement elem in doc.all
                            where elem is HTMLInputButtonElement && elem.getAttribute("value") == "戻る"
                            select elem).FirstOrDefault();
                item.click();
            }
        }
    }
}
