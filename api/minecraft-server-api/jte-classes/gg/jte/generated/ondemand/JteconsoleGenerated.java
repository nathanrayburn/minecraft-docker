package gg.jte.generated.ondemand;
public final class JteconsoleGenerated {
	public static final String JTE_NAME = "console.jte";
	public static final int[] JTE_LINE_INFO = {13,13,13,13,13,13,13,16,16,28,28,40};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		jteOutput.writeContent("<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    <title>API Home Page</title>\n    <link href=\"output.css\" rel=\"stylesheet\">\n    <link href=\"custom.css\" rel=\"stylesheet\">\n    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n</head>\n<body class=\"bg-gray-100\">\n<div class=\"flex\">\n    <nav class=\"bg-gray-200 w-64 h-screen overflow-auto\">\n        ");
		gg.jte.generated.ondemand.JtenavbarGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n    </nav>\n    <div class=\"main-content flex-grow\">\n        ");
		gg.jte.generated.ondemand.JteheaderGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n        <div class=\"console bg-black text-white p-4 rounded-lg mb-4 overflow-auto\" style=\"max-height: 75vh; width: 100%; max-width: 100%;\">\n            <pre id=\"console-output\" class=\"max-w-full mx-auto break-words whitespace-pre-wrap\"></pre>\n        </div>\n\n\n\n        <form id=\"commandForm\">\n            <textarea id=\"commandInput\" class=\"w-full p-2 rounded-lg\" rows=\"2\"></textarea>\n        </form>\n\n        <script src=\"websocket.js\"></script>\n        ");
		gg.jte.generated.ondemand.JtefooterGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n    </div>\n</div>\n\n<script>\n    $(document).ready(function(){\n        $('#commandForm').on('submit', function(e){\n            e.preventDefault();\n        });\n    });\n</script>\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}