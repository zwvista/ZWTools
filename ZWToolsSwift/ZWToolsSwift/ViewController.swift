//
//  ViewController.swift
//  ZWToolsSwift
//
//  Created by 趙偉 on 2018/01/14.
//  Copyright © 2018年 趙偉. All rights reserved.
//

import Cocoa
import WebKit

class ViewController: NSViewController, WKNavigationDelegate {

    @IBOutlet weak var webView: WKWebView!
    
    let home = "http://ufcpp.net/study/csharp/"
    
    override func viewDidLoad() {
        super.viewDidLoad()

        webView.navigationDelegate = self
        webView.load(URLRequest(url: URL(string: home)!))
    }

    override var representedObject: Any? {
        didSet {
        // Update the view, if already loaded.
        }
    }

    func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
        let urlString = webView.url!.absoluteString
        print(urlString)
        guard urlString.starts(with: home) else {return}
        // https://stackoverflow.com/questions/34751860/get-html-from-wkwebview-in-swift
        webView.evaluateJavaScript("document.documentElement.outerHTML.toString()", completionHandler: { [unowned self] (html, error) -> Void in
            if urlString == self.home {
                let text: String = html! as! String
                // https://stackoverflow.com/questions/27880650/swift-extract-regex-matches
                let regex = try! NSRegularExpression(pattern: "<li><a href=\"/study/csharp/(.+?)/(.+?)\">(.+?)</a></li>")
                let matches = regex.matches(in: text, range: NSRange(text.startIndex..., in: text)) as [NSTextCheckingResult]
                let addresses = matches.map{ match -> (String, String) in
                    func group(_ n: Int) -> String {return String(text[Range(match.range(at: n), in: text)!])}
                    // return "\(self.home)/\(group(1))/\(group(2))/"
                    return (group(1), group(2))
                }
            } else {
                
            }
        })
    }

}

