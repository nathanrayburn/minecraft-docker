package gg.jte.generated.ondemand;
public final class JtenavbarGenerated {
	public static final String JTE_NAME = "navbar.jte";
	public static final int[] JTE_LINE_INFO = {12,12,12,12,12,12};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		jteOutput.writeContent("<nav class=\"w-64 h-screen fixed left-0 top-0 overflow-auto\">\n    <div class=\"px-6 py-4\">\n        <h2 class=\"text-lg font-semibold text-gray-900\">MC API</h2>\n        <ul class=\"mt-6\">\n            <li class=\"mb-4\">\n                <a href=\"#\" class=\"px-4 py-2 border border-blue-500 text-blue-500 rounded hover:bg-blue-500 hover:text-white\">Home</a>\n            </li>\n            <li class=\"mb-4\">\n                <a href=\"#\" class=\"px-4 py-2 border border-blue-500 text-blue-500 rounded hover:bg-blue-500 hover:text-white\">Console</a>\n            </li>\n        </ul>\n    </div>\n</nav>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
