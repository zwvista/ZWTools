using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Http;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Web;
using System.Reactive.Linq;
using System.Reactive.Threading.Tasks;

namespace aa
{
    class MainClass
    {
        public static async Task Main(string[] args)
        {
            var client = new HttpClient
            {
                BaseAddress = new Uri("https://github.com/btroncone/learn-rxjs")
            };
            var str = await client.GetStringAsync("");
            var r = new Regex(@"<li><a href=""(/btroncone/learn-rxjs/blob/master/operators/\w+/\w+.md)"">(\w+)</a></li>");
            var r2 = new Regex(@"<div class=""highlight highlight-source-js""><pre>((?:.|\n)+?)</pre></div>");
            var r3 = new Regex("<.+?>");
            var r4 = new Regex("import .+");
            var r5 = new Regex("(//)(.+)");
            r.Matches(str).Cast<Match>()
             .Select(m =>
             {
                 var g = m.Groups.Cast<Group>().ToList();
                 return new { Url = g[1].Value, Name = g[2].Value };
            }).ForEach(o =>
                client.GetStringAsync(o.Url).ToObservable().SelectMany(str2 =>
                {
                    var examples = r.Matches(str2).Cast<Match>()
                     .Select(m =>
                     {
                         var g = m.Groups.Cast<Group>().ToList();
                         return new { Url = g[1].Value, Name = g[2].Value };
                     }).ToObservable().SelectMany(o2 =>
                          client.GetStringAsync(o2.Url).ToObservable().Select(str3 =>
                          {
                              var examples2 = r2.Matches(str3).Cast<Match>()
                                  .Select(m => HttpUtility.HtmlDecode(m.Groups.Cast<Group>().ElementAt(1).Value))
                                   .SelectMany((s, i) => r5.Replace(r4.Replace(r3.Replace(s, "").Replace("// RxJS v6+\n", ""), ""), "$1 $2")
                                       .Split('\n').SkipWhile(s2 => string.IsNullOrWhiteSpace(s2))
                                               .Select(s2 => "    " + s2).StartWith($"  {o2.Name}{i + 1}() {{").Concat(new[] { "  }", "" })
                                               .ToList())
                                   .ToList();
                              return examples2;
                          })).SelectMany(o2 => o2).ToList();
                    return examples;
            }).Subscribe(examples => {
                File.WriteAllLines($"/Users/zhaowei/Documents/Programs/Tools/{o.Name}.ts", examples);
                Console.WriteLine($"Finished {o.Name}");
            }));
            Console.ReadKey();
        }
    }
}
