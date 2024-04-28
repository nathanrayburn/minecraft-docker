package gg.jte.generated.ondemand;
public final class JtefooterGenerated {
	public static final String JTE_NAME = "footer.jte";
	public static final int[] JTE_LINE_INFO = {2,2,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		jteOutput.writeContent("<footer class=\"bg-white p-6 text-center\">\n    <p class=\"text-gray-500\">© 2024 My Server API. All rights reserved.</p>\n</footer>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
